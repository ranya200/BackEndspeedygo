package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Promotion;

import java.util.List;

public interface IPromotionService {

    public Promotion ajouterPromotion(Promotion p);
    public List<Promotion> listPromotions();
    public Promotion getPromotion(String id) ;
    public Promotion updatePromotion(Promotion p);
    public void deletePromotion(String id);
    public Promotion createPromotionAndAssignToProduct(String productId, Promotion promotion) ;
    }
    Promotion createPromotion(Promotion promotion);
    Promotion updatePromotion(String id, Promotion promotion);
    void deletePromotion(String id);
    List<Promotion> getAllPromotions();
    Promotion getPromotionById(String id);
}
