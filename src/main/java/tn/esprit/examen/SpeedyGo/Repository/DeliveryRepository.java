package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.examen.SpeedyGo.entities.Delivery;
import tn.esprit.examen.SpeedyGo.entities.PamentStatus;
import tn.esprit.examen.SpeedyGo.entities.Status;

import java.util.List;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    List<Delivery> findByDriverId(String driverId);
    List<Delivery> findByUserId(String userId);
    List<Delivery> findByPamentStatus(PamentStatus pamentStatus);


}
