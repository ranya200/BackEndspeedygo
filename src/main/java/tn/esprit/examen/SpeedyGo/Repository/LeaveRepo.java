package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Leave;

import tn.esprit.examen.SpeedyGo.entities.Status;

import java.util.List;

@Repository

public interface LeaveRepo extends MongoRepository<Leave, String> {

    List<Leave> findByDriverId(String driverId);
}
