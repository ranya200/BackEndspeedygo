package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IPaymentService;
import tn.esprit.examen.SpeedyGo.entities.Payment;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {
    IPaymentService paymentService;

    @Autowired
    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment p) {
        return paymentService.addPayment(p);
    }

    @PutMapping("/updatePayment")
    public Payment updatePayment(@RequestBody Payment p) {
        return paymentService.updatePayment(p);
    }

    @DeleteMapping("/deletePayment/{id}")
    public void deletePayment(@PathVariable("id") String id) {
        paymentService.deletePayment(id);
    }

    @GetMapping("/getPayment/{id}")
    public Payment getPayment(@PathVariable("id") String id) {
        return paymentService.getPayment(id);
    }

    @GetMapping("/listPayments")
    public Iterable<Payment> listPayments() {
        return paymentService.listPayments();
    }
}
