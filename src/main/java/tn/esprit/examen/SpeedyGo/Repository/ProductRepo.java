package tn.esprit.examen.SpeedyGo.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Product;

import java.util.List;

@Repository

public interface ProductRepo extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);

}
