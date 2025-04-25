package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Repository.OrderRepo;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.Services.OrderService;
import tn.esprit.examen.SpeedyGo.entities.Order;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;
import tn.esprit.examen.SpeedyGo.entities.User;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepo orderRepo;
    private final UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        User user = userRepository.findById(order.getUserId()).orElse(null);
        if (user != null) {
            order.setUserFirstName(user.getFirstName());
            order.setUserLastName(user.getLastName());
        }

        Order saved = orderRepo.save(order);
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


}
