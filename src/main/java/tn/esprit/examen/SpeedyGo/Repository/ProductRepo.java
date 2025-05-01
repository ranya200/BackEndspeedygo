package tn.esprit.examen.SpeedyGo.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Product;
<<<<<<< HEAD
import tn.esprit.examen.SpeedyGo.entities.ProductStatus;
=======
>>>>>>> origin/main

import java.util.List;

@Repository

public interface ProductRepo extends MongoRepository<Product, String> {
    List<Product> findByCategory(String category);
<<<<<<< HEAD
    List<Product> findByStatus(ProductStatus status);
    List<Product> findByCategoryAndStatus(String category, ProductStatus status);
    List<Product> findByPartnerNameAndStatus(String partnerName, ProductStatus status);
=======
>>>>>>> origin/main

}
