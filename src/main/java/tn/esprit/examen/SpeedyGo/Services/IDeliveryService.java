package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Delivery;

import java.util.List;

public interface IDeliveryService {
    Delivery addDelivery(Delivery delivery);
    void deleteDelivery(String idD);
    Delivery modifyDelivery(Delivery delivery);
    List<Delivery> getAllDeliveries();
    Delivery getDelivery(String deliveryId);
}
