package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.OrderService;
import tn.esprit.examen.SpeedyGo.entities.Order;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // Endpoint pour créer une commande (POST /orders)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // Endpoint pour récupérer une commande par son ID (GET /orders/{id})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrder(@PathVariable("id") String id) {
        return orderService.getOrder(id);
    }

    // Endpoint pour lister toutes les commandes (GET /orders)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> listOrders() {
        return orderService.listOrders();
    }

    // Endpoint optionnel pour mettre à jour le statut d'une commande (PUT /orders/{id}/updateStatus)
    @PutMapping(value = "/{id}/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order updateOrderStatus(@PathVariable("id") String id, @RequestParam("status") PackageStatus status) {
        return orderService.updateOrderStatus(id, status);
    }
}
