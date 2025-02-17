package tn.esprit.examen.SpeedyGo.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IDeliveryService;
import tn.esprit.examen.SpeedyGo.Services.IPromotionService;
import tn.esprit.examen.SpeedyGo.entities.Delivery;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    IDeliveryService deliveryService;
    @Autowired
    public DeliveryController(IDeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
    @GetMapping("/retrieve-all-deliveries")
    public List<Delivery> getDeliveries() {
        List<Delivery> listdeliveries = deliveryService.getAllDeliveries();
        return listdeliveries;
    }
    @GetMapping("/retrieve-delivery/{delivery-id}")
    public Delivery getDelivery(@PathVariable("delivery-id") String deliveryId) {
        Delivery delivery = deliveryService.getDelivery(deliveryId);
        return delivery;
    }
    @PostMapping("/add-delivery")
    public Delivery addDelivery(@RequestBody Delivery d) {
        Delivery delivery = deliveryService.addDelivery(d);
        return delivery;
    }
    @DeleteMapping("/remove-delivery/{delivery-id}")
    public void removeDelivery(@PathVariable("delivery-id") String dId) {
        deliveryService.deleteDelivery(dId);
    }

    @PutMapping("/modify-delivery")
    public Delivery modifydelivery(@RequestBody Delivery d) {
        Delivery delivery = deliveryService.modifyDelivery(d);
        return delivery;
    }

}
