package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.examen.SpeedyGo.entities.Complaint;

public interface ComplaintRepo extends MongoRepository<Complaint, String> {
}
