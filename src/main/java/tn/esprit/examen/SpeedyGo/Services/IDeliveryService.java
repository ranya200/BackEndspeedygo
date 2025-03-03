package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Delivery;
import tn.esprit.examen.SpeedyGo.entities.PamentStatus;
import tn.esprit.examen.SpeedyGo.entities.Status;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;

public interface IDeliveryService {
    Delivery addDelivery(Delivery delivery);
    void deleteDelivery(String idD);
    Delivery modifyDelivery(String idD,Delivery updateddelivery);
    List<Delivery> getAllDeliveries();
    Delivery getDelivery(String deliveryId);
    List<Delivery> getDeliveriesForDriver(String driverId);
    List<Delivery> getDeliveriesForUser(String userId);
    public Delivery modifystatusDelivary(Delivery delivery);
    void approveDelivery(String deliveryId);
    void rejectDelivery(String deliveryId);

    List<Delivery> searchDeliveries(PamentStatus pamentStatus);
}
