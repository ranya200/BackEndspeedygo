package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Panier;

@Repository

public interface PanierRepo extends MongoRepository<Panier, String> {
}
