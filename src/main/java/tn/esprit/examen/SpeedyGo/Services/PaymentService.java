package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.PaymentRepo;
import tn.esprit.examen.SpeedyGo.entities.Payment;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService implements IPaymentService {
    PaymentRepo paymentRepo;


    @Override
    public Payment addPayment(Payment p) {
        return paymentRepo.save(p);
    }

    @Override
    public Payment updatePayment(Payment p) {
        return paymentRepo.save(p);
    }

    @Override
    public void deletePayment(String id) {
        paymentRepo.deleteById(id);
    }

    @Override
    public Payment getPayment(String id) {
        return paymentRepo.findById(id).orElse(null);
    }

    @Override
    public List<Payment> listPayments() {
        return paymentRepo.findAll();
    }
}
