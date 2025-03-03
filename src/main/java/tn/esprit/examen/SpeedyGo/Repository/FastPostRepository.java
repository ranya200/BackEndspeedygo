package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tn.esprit.examen.SpeedyGo.entities.FastPost;
import tn.esprit.examen.SpeedyGo.entities.Status;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;

public interface FastPostRepository extends MongoRepository<FastPost, String> {
}
