package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.RideRequest;

import java.util.List;

@Repository
public interface RideRequestRepository extends MongoRepository<RideRequest, String> {
    List<RideRequest> findByStatus(String status);
}
