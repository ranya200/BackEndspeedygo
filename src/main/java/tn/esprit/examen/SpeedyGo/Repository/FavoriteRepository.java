package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Favorite;

import java.util.List;
import java.util.Optional;

@Repository

public interface FavoriteRepository extends MongoRepository<Favorite, String> {
    List<Favorite> findByUserId(String userId);
    Optional<Favorite> findByUserIdAndRideId(String userId, String rideId);
    void deleteByUserIdAndRideId(String userId, String rideId);
}