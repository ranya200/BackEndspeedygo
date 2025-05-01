package tn.esprit.examen.SpeedyGo.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Payment;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> origin/main

@Repository

public interface PaymentRepo extends MongoRepository<Payment, String> {
<<<<<<< HEAD
    List<Payment> findByUserId(String userId);

=======
>>>>>>> origin/main
}
