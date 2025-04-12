package tn.esprit.examen.SpeedyGo.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import java.util.List;

@Repository

public interface PaymentRepo extends MongoRepository<Payment, String> {
    List<Payment> findByUserId(String userId);

}
