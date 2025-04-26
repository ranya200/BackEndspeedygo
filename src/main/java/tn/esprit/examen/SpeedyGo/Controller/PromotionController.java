package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IProductService;
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

    @PostMapping
    public Promotion create(@RequestBody Promotion promotion) {
        return promotionService.createPromotion(promotion);
    }

    @PutMapping("/{id}")
    public Promotion update(@PathVariable String id, @RequestBody Promotion promotion) {
        return promotionService.updatePromotion(id, promotion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        promotionService.deletePromotion(id);
    }

    @GetMapping
    public List<Promotion> getAll() {
        return promotionService.getAllPromotions();
    }

    @GetMapping("/{id}")
    public Promotion getById(@PathVariable String id) {
        return promotionService.getPromotionById(id);
    }


}
