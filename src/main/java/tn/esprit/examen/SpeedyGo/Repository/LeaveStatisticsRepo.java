package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Leave;
import tn.esprit.examen.SpeedyGo.entities.LeaveStatistics;

@Repository
public interface LeaveStatisticsRepo extends MongoRepository<LeaveStatistics, String> {

}
