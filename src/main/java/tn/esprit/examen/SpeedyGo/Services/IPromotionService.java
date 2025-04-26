package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Promotion;

import java.util.List;

public interface IPromotionService {

    Promotion createPromotion(Promotion promotion);
    Promotion updatePromotion(String id, Promotion promotion);
    void deletePromotion(String id);
    List<Promotion> getAllPromotions();
    Promotion getPromotionById(String id);
}
