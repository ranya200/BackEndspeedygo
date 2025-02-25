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

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service

public class OrderService implements IOrderService {

    private final OrderRepo orderRepository;
    // Supposons que vous avez un ProductService pour accéder aux produits
    private final IProductService productService;

    /**
     * Crée une commande après validation et calcul du prix total.
     *
     * @param order La commande à créer avec ses OrderItems
     * @return La commande sauvegardée
     */
    public Order createOrder(Order order) {
        List<OrderItem> items = order.getItems();
        float totalPrice = 0;

        // Parcourir chaque OrderItem pour validation et calcul
        for (OrderItem item : items) {
            // Vérifier l'existence du produit
            Product product = productService.getProduct(item.getProductId());
            if (product == null) {
                throw new RuntimeException("Produit avec l'ID " + item.getProductId() + " introuvable.");
            }
            // Vérifier la disponibilité du stock
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Stock insuffisant pour le produit " + product.getName());
            }
            // Calcul du prix pour cet item (on peut utiliser le prix actuel du produit ou celui stocké dans OrderItem)
            totalPrice += product.getPrice() * item.getQuantity();
        }

        // Mettre à jour la commande avec le prix total, la date et le statut initial
        order.setPrice(totalPrice);
        order.setDate(new Date());
        order.setStatus(PackageStatus.PENDING);

        // Sauvegarder la commande dans MongoDB
        return orderRepository.save(order);
    }

    /**
     * Récupère une commande par son identifiant.
     *
     * @param id L'identifiant de la commande
     * @return La commande correspondante ou null si non trouvée
     */
    public Order getOrder(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    /**
     * Liste toutes les commandes.
     *
     * @return Une liste de commandes
     */
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(String id, PackageStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande avec l'ID " + id + " introuvable."));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
}
