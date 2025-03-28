package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.ProductRepo;
import tn.esprit.examen.SpeedyGo.Repository.PromotionRepository;
import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;
import tn.esprit.examen.SpeedyGo.entities.Promotion;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class PromotionService implements IPromotionService {

    PromotionRepository promotionRepository;
    ProductRepo productRepo;

    @Override
    public Promotion ajouterPromotion(Promotion p) {
        return promotionRepository.save(p);
    }

    @Override
    public Promotion updatePromotion(Promotion p) {
        return promotionRepository.save(p);
    }

    @Override
    public void deletePromotion(String id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Promotion getPromotion(String id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Promotion> listPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion createPromotionAndAssignToProduct(String productId, Promotion promotion) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        promotion = promotionRepository.save(promotion);
        product.setPromotion(promotion);
        if (isPromotionActive(promotion)) {
            double discountedPrice = applyDiscount(product.getPrice(), promotion);
            product.setDiscountedPrice(discountedPrice); // Assume there is a field to store discounted price
        }
        productRepo.save(product);
        return promotion;
    }

    private boolean isPromotionActive(Promotion promotion) {
        Date now = new Date();
        return !now.before(promotion.getStartDate()) && !now.after(promotion.getEndDate());
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
