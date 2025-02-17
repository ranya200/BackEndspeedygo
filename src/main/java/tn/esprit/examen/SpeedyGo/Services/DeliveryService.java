package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.DeliveryRepository;
import tn.esprit.examen.SpeedyGo.entities.Delivery;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryService implements IDeliveryService{
    DeliveryRepository deliveryRepository;
    public Delivery addDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public void deleteDelivery(String idD) {
        deliveryRepository.deleteById(idD);
    }

    public Delivery modifyDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries;
    }
    public Delivery getDelivery(String deliveryId) {
        return deliveryRepository.findById(deliveryId).get();
    }


}
