package tn.esprit.examen.SpeedyGo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
=======
>>>>>>> origin/main
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IDeliveryService;
<<<<<<< HEAD
import tn.esprit.examen.SpeedyGo.Services.IPromotionService;
=======
>>>>>>> origin/main
import tn.esprit.examen.SpeedyGo.entities.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/delivery")
@CrossOrigin(origins = "http://localhost:4200")

public class DeliveryController {
<<<<<<< HEAD
    IDeliveryService deliveryService;
=======
    @Autowired
    private DeliveryAssignmentService assignmentService;

    IDeliveryService deliveryService;

>>>>>>> origin/main
    @Autowired
    public DeliveryController(IDeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
<<<<<<< HEAD
=======

>>>>>>> origin/main
    @GetMapping("/retrieve-all-deliveries")
    public List<Delivery> getdeliveries() {
        List<Delivery> listdeliveries = deliveryService.getAllDeliveries();
        return listdeliveries;
    }
<<<<<<< HEAD
=======

>>>>>>> origin/main
    @GetMapping("/retrieve-delivery/{delivery-id}")
    public Delivery getDelivery(@PathVariable("delivery-id") String deliveryId) {
        Delivery delivery = deliveryService.getDelivery(deliveryId);
        return delivery;
    }
<<<<<<< HEAD
=======

>>>>>>> origin/main
    @PostMapping("/add-delivery")
    public Delivery addDelivery(@RequestBody Delivery d) {
        Delivery delivery = deliveryService.addDelivery(d);
        return delivery;
    }
<<<<<<< HEAD
=======

>>>>>>> origin/main
    @DeleteMapping("/remove-delivery/{delivery-id}")
    public ResponseEntity<?> removeDelivery(@PathVariable("delivery-id") String dId) {
        try {
            deliveryService.deleteDelivery(dId);
            return ResponseEntity.ok("🚀 Delivery deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
<<<<<<< HEAD
=======

>>>>>>> origin/main
    @PutMapping("/modify-delivery/{delivery-id}")
    public Delivery modifydelivery(@PathVariable("delivery-id") String dId, @RequestBody Delivery d) {
        return deliveryService.modifyDelivery(dId, d);
    }

    @Operation(summary = "Get deliveries for a driver")
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Delivery>> getDeliveriesForDriver(
            @Parameter(description = "ID of the driver", required = true)
            @PathVariable String driverId) {
        List<Delivery> deliveries = deliveryService.getDeliveriesForDriver(driverId);
        return ResponseEntity.ok(deliveries);
    }
<<<<<<< HEAD
=======

>>>>>>> origin/main
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Delivery>> getDeliveriesForUser(@PathVariable String userId) {
        List<Delivery> deliveries = deliveryService.getDeliveriesForUser(userId);
        return ResponseEntity.ok(deliveries);
    }

    @PutMapping("/approve/{id}")
    public void approveDelivery(@PathVariable String id) {
        deliveryService.approveDelivery(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectDelivery(@PathVariable String id) {
        deliveryService.rejectDelivery(id);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<Delivery>> searchDeliveries(@RequestParam Optional<String> pamentStatus) {
        if (pamentStatus.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Prevent empty query parameters
        }

        try {
            PamentStatus enumPamentStatus = PamentStatus.valueOf(pamentStatus.get().toUpperCase()); // Convert to uppercase
            System.out.println("🔍 Received valid pamentStatus: " + enumPamentStatus);

            List<Delivery> deliveries = deliveryService.searchDeliveries(enumPamentStatus);

            if (deliveries.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(deliveries);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ ERROR: Invalid pamentStatus received: " + pamentStatus.get());
            return ResponseEntity.badRequest().body(null);
        }
    }

<<<<<<< HEAD
=======
  //  @PutMapping("/assign/{deliveryId}")
   // public ResponseEntity<?> assignDeliveryAutomatically(@PathVariable String deliveryId) {
      //  try {
           // Delivery assigned = assignmentService.assignDelivery(deliveryId);
          //  return ResponseEntity.ok(assigned);
       // } catch (RuntimeException e) {
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      //  }
    //}

    @PutMapping("/assign-pending")
    public ResponseEntity<Void> assignPendingDeliveries() {
        deliveryService.assignDeliveriesAutomatically();
        return ResponseEntity.ok().build();
    }
>>>>>>> origin/main


}
