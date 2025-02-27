package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.DeliveryRepository;
import tn.esprit.examen.SpeedyGo.entities.Delivery;
import tn.esprit.examen.SpeedyGo.entities.FastPost;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Delivery> getDeliveriesForDriver(String driverId) {
        return deliveryRepository.findByDriverId(driverId);
    }
    @Override
    public List<Delivery> getDeliveriesForUser(String userId) {
        return deliveryRepository.findByUserId(userId);
    }
    public Delivery modifystatusDelivary(Delivery delivery){
        if (delivery.getIdD() != null) {
            Optional<Delivery> existingDelivary = deliveryRepository.findById(delivery.getIdD());
            if (existingDelivary.isPresent()) {
                Delivery deliveryToUpdate= existingDelivary.get();
                deliveryToUpdate.setDeliveryStatus(delivery.getDeliveryStatus());
                // update other fields as necessary
                return deliveryRepository.save(deliveryToUpdate);
            }
        }
        return null;
    }

}
