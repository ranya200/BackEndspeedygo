package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Leave;

@Repository

public interface LeaveRepo extends MongoRepository<Leave, String> {
}
