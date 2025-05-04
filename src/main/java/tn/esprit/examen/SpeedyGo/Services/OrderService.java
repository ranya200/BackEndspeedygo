package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.entities.Order;
import tn.esprit.examen.SpeedyGo.entities.OrderItem;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;
import tn.esprit.examen.SpeedyGo.entities.Product;
import tn.esprit.examen.SpeedyGo.Repository.OrderRepo;
import tn.esprit.examen.SpeedyGo.Repository.ProductRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service

public class OrderService implements IOrderService {

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    public Order createOrder(Order order) {
        Order savedOrder = orderRepo.save(order);

        // ✅ Mettre à jour stock et ventes
        for (OrderItem item : order.getItems()) {
            Product product = productRepo.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produit introuvable : " + item.getProductId()));

            int newStock = product.getStockQuantity() - item.getQuantity();
            if (newStock < 0) {
                throw new RuntimeException("Stock insuffisant pour le produit : " + product.getName());
            }

            product.setStockQuantity(newStock);
            product.setPreviousSales(product.getPreviousSales() + item.getQuantity());

            productRepo.save(product); // ✅ sauvegarder modifications
        }

        return savedOrder;
    }

    public Order getOrderById(String orderId) {
        return orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }


    public void updateOrderStatus(String orderId, PackageStatus status) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            orderRepo.save(order);
            log.info("✅ Statut de la commande {} mis à jour : {}", orderId, status);
        } else {
            log.warn("❌ Commande non trouvée pour orderId : {}", orderId);
        }
    }
}
