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
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class PromotionService implements IPromotionService {

    PromotionRepository promotionRepository;

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

}
