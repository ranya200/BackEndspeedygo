package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.PromotionRepository;
import tn.esprit.examen.SpeedyGo.entities.Promotion;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class PromotionService implements IPromotionService {

    PromotionRepository promotionRepository;

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
}
