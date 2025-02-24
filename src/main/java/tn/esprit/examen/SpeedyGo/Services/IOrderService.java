package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Order;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;

import java.util.List;

public interface IOrderService {

    Order createOrder(Order order);
    Order getOrder(String id);
    List<Order> listOrders();
    Order updateOrderStatus(String id, PackageStatus newStatus);
}
