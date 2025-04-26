package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.RideRatings;

import java.util.List;

@Repository

public interface RatingRepository extends MongoRepository<RideRatings, String> {
    List<RideRatings> findByDriverId(String driverId);
    List<RideRatings> findByRideId(String rideId);
    boolean existsByRideIdAndPassengerId(String rideId, String passengerId);
}

