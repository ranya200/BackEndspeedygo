package tn.esprit.examen.SpeedyGo.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Payment;

@Repository

public interface PaymentRepo extends MongoRepository<Payment, String> {
}
