package tn.esprit.examen.SpeedyGo.Controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.PaymentService;
import tn.esprit.examen.SpeedyGo.Repository.PaymentRepo;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.PaymentType;

import java.util.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/processPayment")
    public Payment processPayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    @PostMapping(value = "/create-checkout-session", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> createCheckoutSession(@RequestBody Payment payment) {
        log.info("👉 Création d'une session Stripe pour: {} €", payment.getAmount());
        try {
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
            lineItems.add(
                    SessionCreateParams.LineItem.builder()
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("eur")
                                            .setUnitAmount((long) (payment.getAmount() * 100))
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName("Paiement SpeedyGo")
                                                            .build()
                                            )
                                            .build()
                            )
                            .setQuantity(1L)
                            .build()
            );

            SessionCreateParams params = SessionCreateParams.builder()
                    .addAllLineItem(lineItems)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(
                            "http://localhost:4200/payment-success"
                                    + "?session_id={CHECKOUT_SESSION_ID}"
                                    + "&amount=" + payment.getAmount()
                                    + "&userId=" + payment.getUserId()
                                    + "&packageId=" + payment.getPackageId()
                    )
                    .setCancelUrl("http://localhost:4200/payment-cancel")
                    .build();

            Session session = Session.create(params);
            Map<String, String> responseData = new HashMap<>();
            responseData.put("checkoutUrl", session.getUrl());

            return ResponseEntity.ok(responseData);
        } catch (StripeException e) {
            log.error("StripeException: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/record-success")
    public Payment recordSuccessfulPayment(@RequestBody Payment payment) {
        log.info("📅 Tentative d'enregistrement d'un paiement : {}", payment);

        payment.setPaymentDate(new Date());
        payment.setStatus(true);

        Payment saved = paymentService.save(payment);
        log.info("✅ Paiement sauvegardé avec succès : {}", saved);

        return saved;
    }

    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        return paymentService.getAll(); // ajoute getAll() dans PaymentService si besoin
    }

}
