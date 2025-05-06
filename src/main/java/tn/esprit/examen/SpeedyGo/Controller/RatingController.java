package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.RatingService;
import tn.esprit.examen.SpeedyGo.entities.RideRatings;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;


    @GetMapping("/badges/global")
    public Map<String, String> getGlobalBadges() {
        return ratingService.getDriverGlobalBadges();
    }


    @GetMapping("/driver/{driverId}")
    public List<RideRatings> getRatingsForDriver(@PathVariable String driverId) {
        return ratingService.getRatingsForDriver(driverId);
    }

    @GetMapping("/ride/{rideId}")
    public List<RideRatings> getRatingsForRide(@PathVariable String rideId) {
        return ratingService.getRatingsForRide(rideId);
    }
    @PostMapping
    public ResponseEntity<?> submitRating(@RequestBody RideRatings rating) {
        RideRatings result = ratingService.submitRating(rating);
        if (result == null) {
            return ResponseEntity.status(409).body("You have already rated this ride.");
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<RideRatings> updateRating(@PathVariable String ratingId, @RequestBody RideRatings updatedRating) {
        RideRatings result = ratingService.updateRating(ratingId, updatedRating);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/ride/{rideId}/user/{passengerId}")
    public ResponseEntity<RideRatings> getUserRatingForRide(@PathVariable String rideId, @PathVariable String passengerId) {
        return ratingService.getRatingForRideByUser(rideId, passengerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
