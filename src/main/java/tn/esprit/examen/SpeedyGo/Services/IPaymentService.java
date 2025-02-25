package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Payment;

import java.util.List;

public interface IPaymentService {
    Payment processPayment(Payment payment);
}
