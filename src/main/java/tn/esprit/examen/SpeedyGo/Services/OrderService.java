package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.OrderRepo;
import tn.esprit.examen.SpeedyGo.entities.Order;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService implements IOrderService {
    OrderRepo orderRepo;

    @Override
    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepo.deleteById(id);
    }

    @Override
    public Order getOrder(String id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public List<Order> listOrders() {
        return orderRepo.findAll();
    }

}
