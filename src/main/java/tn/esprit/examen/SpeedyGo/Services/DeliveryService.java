package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.DeliveryRepository;
import tn.esprit.examen.SpeedyGo.entities.*;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.DeliveryRepository;
import tn.esprit.examen.SpeedyGo.Repository.FastPostRepository;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.entities.*;

import java.time.LocalDateTime;
>>>>>>> origin/main
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
<<<<<<< HEAD
public class DeliveryService implements IDeliveryService{
    DeliveryRepository deliveryRepository;
=======

public class DeliveryService implements IDeliveryService{
    DeliveryRepository deliveryRepository;

    @Autowired
    UserRepository userRepository;
    FastPostRepository fastPostRepository;
>>>>>>> origin/main
    public Delivery addDelivery(Delivery delivery) {
        if (delivery.getIdD() != null && deliveryRepository.existsById(delivery.getIdD())) {
            throw new RuntimeException("❌ Cannot create delivery: ID already exists!");
        }

        delivery.setIdD(null); // Force MongoDB to generate a new ID
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteDelivery(String idD) {
        if (!deliveryRepository.existsById(idD)) {
            throw new RuntimeException("❌ Delivery not found with ID: " + idD);
        }
        deliveryRepository.deleteById(idD);

    }

    public Delivery modifyDelivery(String idD,Delivery updateddelivery) {
        return deliveryRepository.findById(idD).map(delivery -> {
            delivery.setDeliveryStatus(updateddelivery.getDeliveryStatus());
            delivery.setEstimatedDeliveryTime(updateddelivery.getEstimatedDeliveryTime());
            delivery.setPamentStatus(updateddelivery.getPamentStatus());
            delivery.setStatus(updateddelivery.getStatus());
            delivery.setDriverId(updateddelivery.getDriverId());
            delivery.setUserId(updateddelivery.getUserId());
            return deliveryRepository.save(delivery);
        }).orElseThrow(() -> new RuntimeException("❌ Vehicle not found with ID: " + idD));
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
    public void approveDelivery(String deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null); // Retrieve the vehicle by ID.findById(id).orElse(null);
        if (delivery != null) {
            delivery.setStatus(Status.APPROVED);
            deliveryRepository.save(delivery);
        }
    }
    public void rejectDelivery(String deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null); // Retrieve the vehicle by ID.findById(id).orElse(null);
        if (delivery != null) {
            delivery.setStatus(Status.REJECTED);
            deliveryRepository.save(delivery);
        }
    }

    public List<Delivery> searchDeliveries(PamentStatus pamentStatus) {
        return deliveryRepository.findByPamentStatus(pamentStatus);
    }

<<<<<<< HEAD
}
=======
    public void assignDeliveriesAutomatically() {
        List<Delivery> unassignedDeliveries = deliveryRepository.findAll()
                .stream()
                .filter(d -> d.getDriverId() == null)
                .toList();

        List<User> availableDrivers = userRepository.findAll()
                .stream()
                .filter(u -> u.getRoles().contains("driver"))
                .filter(u -> u.getDailyDeliveriesCount() < 5)
                .toList();

        for (Delivery delivery : unassignedDeliveries) {
            // Trouver un driver qui a encore des créneaux de livraison disponibles
            Optional<User> optionalDriver = availableDrivers.stream()
                    .filter(driver -> driver.getDailyDeliveriesCount() < 5)
                    .findFirst();

            if (optionalDriver.isEmpty()) break;

            User driver = optionalDriver.get();

            // Affecter au driver
            delivery.setDriverId(driver.getId());
            delivery.setDriverFirstName(driver.getFirstName());
            delivery.setDriverLastName(driver.getLastName());

            deliveryRepository.save(delivery);

            // Mettre à jour le compteur du driver
            driver.setDailyDeliveriesCount(driver.getDailyDeliveriesCount() + 1);
            userRepository.save(driver);
        }
    }



    @Scheduled(cron = "0 0 0 * * *")
    public void resetDailyDeliveryCounts() {
        List<User> drivers = userRepository.findAll().stream()
                .filter(u -> u.getRoles().contains("driver"))
                .toList();

        drivers.forEach(d -> d.setDailyDeliveriesCount(0));
        userRepository.saveAll(drivers);

        System.out.println("🕛 Daily delivery counters reset.");
    }



}


>>>>>>> origin/main
