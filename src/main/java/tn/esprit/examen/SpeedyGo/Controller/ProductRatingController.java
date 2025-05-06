package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.ProductRatingService;
import tn.esprit.examen.SpeedyGo.entities.ProductRating;

import java.util.Map;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class ProductRatingController {

    private final ProductRatingService productRatingService;

    @PostMapping("/rate")
    public ProductRating rateProduct(@RequestBody Map<String, Object> payload) {
        String userId = (String) payload.get("userId");
        String productId = (String) payload.get("productId");
        int rating = (int) payload.get("rating");
        return productRatingService.rateProduct(userId, productId, rating);
    }

    @GetMapping("/average/{productId}")
    public double getAverage(@PathVariable String productId) {
        return productRatingService.getAverageRating(productId);
    }

    @GetMapping("/user/{userId}/product/{productId}")
    public ProductRating getUserRating(@PathVariable String userId, @PathVariable String productId) {
        return productRatingService.getUserRatingForProduct(userId, productId).orElse(null);
    }
}