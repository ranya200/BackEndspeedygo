package tn.esprit.examen.SpeedyGo.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Promotion;

import java.time.LocalDate;
import java.util.Optional;

@Repository

public interface PromotionRepository extends MongoRepository<Promotion, String> {
    @Query("{ 'productId': ?0, 'startDate': { $lte: ?1 }, 'endDate': { $gte: ?1 } }")
    Promotion findActivePromotionByProductId(String productId, LocalDate currentDate);

    @Query("{$and: ["
            + "{ 'productId': ?0 },"
            + "{ 'startDate': { $lte: ?1 } },"
            + "{ 'endDate': { $gte: ?1 } }"
            + "]}")
    Optional<Promotion> findActivePromotionForProduct(String productId, LocalDate today);


}
