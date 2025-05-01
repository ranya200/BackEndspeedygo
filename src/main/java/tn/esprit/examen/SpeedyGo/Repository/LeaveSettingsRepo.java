package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.LeaveSettings;

@Repository

public interface LeaveSettingsRepo extends MongoRepository<LeaveSettings, String> {
}
