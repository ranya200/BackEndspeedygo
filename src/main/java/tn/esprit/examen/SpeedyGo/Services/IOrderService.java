package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Order;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;

import java.util.List;

public interface IOrderService {

    void updateOrderStatus(String orderId, PackageStatus status);
}
