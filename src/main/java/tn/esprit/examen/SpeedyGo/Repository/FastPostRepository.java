package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.examen.SpeedyGo.entities.FastPost;

public interface FastPostRepository extends MongoRepository<FastPost, String> {
}
