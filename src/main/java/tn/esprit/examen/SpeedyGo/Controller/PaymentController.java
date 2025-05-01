package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.PaymentService;
import tn.esprit.examen.SpeedyGo.entities.Payment;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

        private final PaymentService paymentService;

        // Endpoint pour traiter un paiement (POST /payments)
        @PostMapping(value = "/processPayment", produces = MediaType.APPLICATION_JSON_VALUE)
        public Payment processPayment(@RequestBody Payment payment) {
            return paymentService.processPayment(payment);
        }

        //Des endpoints GET pour consulter l’historique des paiements si nécessaire.
    }


