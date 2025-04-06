package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Order;

import java.util.List;

@Repository

public interface OrderRepo extends MongoRepository<Order, String> {
    List<Order> findByUserId(String userId);

}
