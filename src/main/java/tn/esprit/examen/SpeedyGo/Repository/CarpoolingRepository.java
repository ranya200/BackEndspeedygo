package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Carpooling;

import java.util.List;

@Repository
public interface CarpoolingRepository extends MongoRepository<Carpooling, String> {
    List<Carpooling> findByPickupLocationAndDropoffLocation(String pickup, String dropoff);
}
