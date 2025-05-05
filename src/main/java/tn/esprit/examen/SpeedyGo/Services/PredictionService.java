package tn.esprit.examen.SpeedyGo.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class PredictionService {

    private final RestTemplate restTemplate = new RestTemplate();

    public int predictSoldFast(int category, float price, int stockQuantity, int previousSales) {
        String url = "http://localhost:5001/predict";  // Ton serveur Flask tourne ici

        // Préparer les données à envoyer
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("category", category);
        requestBody.put("price", price);
        requestBody.put("stockQuantity", stockQuantity);
        requestBody.put("previousSales", previousSales);

        // Préparer les headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // Envoyer la requête POST
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> body = response.getBody();
            Integer prediction = (Integer) body.get("soldFastPrediction");
            return prediction;
        } else {
            throw new RuntimeException("Erreur lors de la prédiction : " + response.getStatusCode());
        }
    }

}
