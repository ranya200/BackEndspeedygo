package tn.esprit.examen.SpeedyGo.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.RatingRepository;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.entities.RideRatings;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepo;
    private final UserRepository userRepo;

    public Map<String, String> getDriverGlobalBadges() {
        Map<String, List<RideRatings>> grouped = ratingRepo.findAll().stream()
                .collect(Collectors.groupingBy(RideRatings::getDriverId));

        Map<String, String> badgeMap = new HashMap<>();

        for (Map.Entry<String, List<RideRatings>> entry : grouped.entrySet()) {
            String driverId = entry.getKey();
            List<RideRatings> ratings = entry.getValue();

            double avg = ratings.stream()
                    .mapToDouble(r -> (r.getComfortScore() + r.getPunctualityScore() + r.getSafetyScore()) / 3.0)
                    .average().orElse(0);

            if (avg >= 4.5) badgeMap.put(driverId, "GOLD");
            else if (avg >= 4.0) badgeMap.put(driverId, "SILVER");
            else if (avg >= 3.0) badgeMap.put(driverId, "BRONZE");
        }

        return badgeMap;
    }


    public RideRatings submitRating(RideRatings rating) {
        if (ratingRepo.existsByRideIdAndPassengerId(rating.getRideId(), rating.getPassengerId())) {
            return null;
        }

        rating.setCreatedAt(LocalDateTime.now());
        RideRatings saved = ratingRepo.save(rating);

        // ❌ Désactivé pour éviter que tous les trajets du conducteur soient touchés
        // updateDriverBadge(rating.getDriverId());

        return saved;
    }

    public List<RideRatings> getRatingsForDriver(String driverId) {
        return ratingRepo.findByDriverId(driverId);
    }

    public List<RideRatings> getRatingsForRide(String rideId) {
        return ratingRepo.findByRideId(rideId);
    }

    // 🟡 Optionnel : à garder si tu veux afficher un badge global dans le profil conducteur
    public void updateDriverBadge(String driverId) {
        List<RideRatings> ratings = ratingRepo.findByDriverId(driverId);
        if (ratings.isEmpty()) return;

        double avg = ratings.stream()
                .mapToDouble(r -> (r.getSafetyScore() + r.getPunctualityScore() + r.getComfortScore()) / 3.0)
                .average()
                .orElse(0);

        String badge;
        if (avg >= 4.5) badge = "GOLD";
        else if (avg >= 4.0) badge = "SILVER";
        else if (avg >= 3.0) badge = "BRONZE";
        else badge = null;

        userRepo.findById(driverId).ifPresent(user -> {
            user.setBadge(badge);
            userRepo.save(user);
        });
    }
}

