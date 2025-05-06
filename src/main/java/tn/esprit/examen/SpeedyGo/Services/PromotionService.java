package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.PromotionRepository;
import tn.esprit.examen.SpeedyGo.entities.Promotion;

import tn.esprit.examen.SpeedyGo.Repository.ProductRepo;
import tn.esprit.examen.SpeedyGo.Repository.PromotionRepository;
import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;
import tn.esprit.examen.SpeedyGo.entities.Promotion;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class PromotionService implements IPromotionService {

    PromotionRepository promotionRepository;

    ProductRepo productRepo;

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion updatePromotion(String id, Promotion promotion) {
        Optional<Promotion> existing = promotionRepository.findById(id);
        if (existing.isPresent()) {
            promotion.setId(id);
            return promotionRepository.save(promotion);
        } else {
            throw new RuntimeException("Promotion not found");
        }
    }

    @Override
    public void deletePromotion(String id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion getPromotionById(String id) {
        return promotionRepository.findById(id).orElseThrow(() -> new RuntimeException("Promotion not found"));
    }

    public double applyDiscount(double originalPrice, Promotion promotion) {
        switch (promotion.getDiscountType()) {
            case POURCENTAGE:
                return originalPrice * (100 - promotion.getDiscount()) / 100;
            case FIXEDAMOUNT:
                return Math.max(0, originalPrice - promotion.getDiscount());  // Ensure the price does not go below zero
            case FREEDELIVERY:
                return originalPrice;  // Assuming free delivery does not affect the price
            default:
                return originalPrice;
        }
    }

    // Optional: Method to calculate the price after the promotion is applied
    private double calculatePriceAfterPromotion(Product product) {
        if (product.getPromotion() != null && isPromotionActive(product.getPromotion())) {
            return applyDiscount(product.getPrice(), product.getPromotion());
        }
        return product.getPrice();
    }


    // maybe not needed
    /*public double getDiscountedPrice(String productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));
        return calculatePriceAfterPromotion(product);
    }*/

}
