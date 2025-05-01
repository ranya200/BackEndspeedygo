package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Repository.OrderRepo;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.Services.OrderService;
import tn.esprit.examen.SpeedyGo.entities.Order;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;
import tn.esprit.examen.SpeedyGo.entities.User;
=======
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.OrderService;
import tn.esprit.examen.SpeedyGo.entities.Order;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;
>>>>>>> origin/main

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
<<<<<<< HEAD
    private final OrderRepo orderRepo;
    private final UserRepository userRepository;
    private final OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        User user = userRepository.findById(order.getUserId()).orElse(null);
        if (user != null) {
            order.setUserFirstName(user.getFirstName());
            order.setUserLastName(user.getLastName());
        }

        Order saved = orderService.createOrder(order); // ✅ appel du service
        return ResponseEntity.ok(saved);
    }



    @GetMapping("/by-user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable String userId) {
        return orderRepo.findByUserId(userId);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }


=======

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
>>>>>>> origin/main
}
