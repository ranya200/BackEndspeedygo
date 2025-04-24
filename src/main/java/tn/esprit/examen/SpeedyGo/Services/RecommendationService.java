package tn.esprit.examen.SpeedyGo.Services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.OrderRepo;
import tn.esprit.examen.SpeedyGo.entities.Order;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
@Service
public class RecommendationService {

    public List<String> recommendProducts(List<String> history, List<String> candidates) {
        try {
            URL url = new URL("http://localhost:5000/recommend");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject requestJson = new JSONObject();
            requestJson.put("history", new JSONArray(history));
            requestJson.put("candidates", new JSONArray(candidates));

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestJson.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            JSONArray responseArray = new JSONArray(response);

            List<String> recommendations = new ArrayList<>();
            for (int i = 0; i < responseArray.length(); i++) {
                recommendations.add(responseArray.getString(i));
            }

            return recommendations;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
