package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.examen.SpeedyGo.entities.ProductRating;

import java.util.List;
import java.util.Optional;

public interface ProductRatingRepo extends MongoRepository<ProductRating, String> {
    Optional<ProductRating> findByUserIdAndProductId(String userId, String productId);
    List<ProductRating> findByProductId(String productId);
    List<ProductRating> findByUserId(String userId);
}
