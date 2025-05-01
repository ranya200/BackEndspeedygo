package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Repository.OrderRepo;
import tn.esprit.examen.SpeedyGo.Repository.ProductRepo;
import tn.esprit.examen.SpeedyGo.Services.RecommendationService;
import tn.esprit.examen.SpeedyGo.entities.Order;
import tn.esprit.examen.SpeedyGo.entities.Product;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    @GetMapping("/{userId}")
    public ResponseEntity<List<String>> getRecommendations(@PathVariable String userId) {
        // 1. Récupérer tous les produits commandés par cet utilisateur
        List<Order> orders = orderRepo.findByUserId(userId);
        List<String> history = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .map(OrderItem::getProductName)
                .distinct()
                .toList();

        // 2. Tous les produits disponibles
        List<String> allProducts = productRepo.findAll().stream()
                .map(Product::getName)
                .distinct()
                .toList();

        // 3. Exclure ceux déjà achetés
        List<String> candidates = allProducts.stream()
                .filter(p -> !history.contains(p))
                .toList();

        List<String> recommended = recommendationService.recommendProducts(history, candidates);
        return ResponseEntity.ok(recommended);
    }
}
