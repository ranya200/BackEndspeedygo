package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IPromotionService;
import tn.esprit.examen.SpeedyGo.entities.Promotion;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/promotion")
public class PromotionController {
    IPromotionService promotionService;

    @Autowired
    public PromotionController(IPromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/addPromotion")
    public Promotion addPromotion(@RequestBody Promotion p) {
        return promotionService.ajouterPromotion(p);
    }


    @PutMapping("/updatePromotion")
    public Promotion updatePromotion(@RequestBody Promotion p) {
        return promotionService.updatePromotion(p);
    }

    @DeleteMapping("/deletePromotion/{id}")
    public void deletePromotion(@PathVariable("id") String id) {
        promotionService.deletePromotion(id);
    }

    @GetMapping("/getPromotion/{id}")
    public Promotion getPromotion(@PathVariable("id") String id) {
        return promotionService.getPromotion(id);
    }

    @GetMapping("/listPromotions")
    public List<Promotion> listPromotions() {
        return promotionService.listPromotions();
    }
}
