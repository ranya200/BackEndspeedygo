package tn.esprit.examen.SpeedyGo.Controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.PredictionService;

@RestController
@RequestMapping("/predict")
@RequiredArgsConstructor
public class PredictionController {

    private final PredictionService predictionService;

    @GetMapping("/test-predict")
    @ApiOperation(value = "Test de la prédiction de vitesse de vente", notes = "Retourne si le produit est vendu rapidement ou non")
    public String testPrediction(
            @RequestParam int category,
            @RequestParam float price,
            @RequestParam int stockQuantity,
            @RequestParam int previousSales) {

        int prediction = predictionService.predictSoldFast(category, price, stockQuantity, previousSales);
        return "Prediction (0=Slow, 1=Fast) : " + prediction;
    }

}
