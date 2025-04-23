package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.ProductRatingRepo;
import tn.esprit.examen.SpeedyGo.entities.ProductRating;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductRatingService {
    private final ProductRatingRepo ratingRepo;

    public ProductRating rateProduct(String userId, String productId, int rating) {
        Optional<ProductRating> existing = ratingRepo.findByUserIdAndProductId(userId, productId);
        ProductRating r = existing.orElse(new ProductRating());
        r.setUserId(userId);
        r.setProductId(productId);
        r.setRating(rating);
        return ratingRepo.save(r);
    }

    public double getAverageRating(String productId) {
        List<ProductRating> ratings = ratingRepo.findByProductId(productId);
        return ratings.stream().mapToInt(ProductRating::getRating).average().orElse(0.0);
    }

    public Optional<ProductRating> getUserRatingForProduct(String userId, String productId) {
        return ratingRepo.findByUserIdAndProductId(userId, productId);
    }
}
