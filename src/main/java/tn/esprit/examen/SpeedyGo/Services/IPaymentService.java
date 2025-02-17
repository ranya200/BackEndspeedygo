package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Payment;

import java.util.List;

public interface IPaymentService {
    Payment addPayment(Payment p);
    Payment updatePayment(Payment p);
    void deletePayment(String id);
    Payment getPayment(String id);
    List<Payment> listPayments();
}
