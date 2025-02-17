package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Order;

import java.util.List;

public interface IOrderService {

    Order addOrder(Order order);

    Order updateOrder(Order order);

    Order getOrder(String id);

    void deleteOrder(String id);

    List<Order> listOrders();
}
