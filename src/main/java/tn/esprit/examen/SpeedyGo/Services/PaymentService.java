package tn.esprit.examen.SpeedyGo.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;
import tn.esprit.examen.SpeedyGo.Repository.PaymentRepo;
import tn.esprit.examen.SpeedyGo.entities.User;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements IPaymentService {

    private final PaymentRepo paymentRepository;
    private final OrderService orderService;

    @Autowired
    private MailService mailService;


    @Autowired
    private UserRepository userRepository;

    public Payment processPayment(Payment payment) {
        log.info("Traitement du paiement de type {} pour un montant de {}", payment.getPaymentType(), payment.getAmount());
        log.info("Paiement de {} € pour l'utilisateur {} et le panier {}", payment.getAmount(), payment.getUserId(), payment.getPackageId());

        boolean paymentSuccess = true;

        payment.setPaymentDate(new Date());
        payment.setStatus(paymentSuccess);

        Payment savedPayment = paymentRepository.save(payment);

        if (paymentSuccess && payment.getOrderId() != null) {
            orderService.updateOrderStatus(payment.getOrderId(), PackageStatus.DELIVERED);
        }

        return savedPayment;
    }

    public Payment save(Payment payment) {
        User user = userRepository.findById(payment.getUserId()).orElse(null);

        if (user != null) {
            payment.setUserFirstName(user.getFirstName());
            payment.setUserLastName(user.getLastName());
        }

        payment.setPaymentDate(new Date());
        Payment saved = paymentRepository.save(payment);

        // Envoi email...
        if (user != null) {
            String emailHtml = mailService.getHtmlPaymentTemplate(user, payment);
            try {
                mailService.sendPaymentConfirmation(user.getEmail(), "Confirmation de paiement", emailHtml);
            } catch (Exception e) {
                log.warn("❌ Email non envoyé : {}", e.getMessage());
            }
        }

        // Mise à jour de la commande liée
        if (payment.getOrderId() != null && !payment.getOrderId().isEmpty()) {
            orderService.updateOrderStatus(payment.getOrderId(), PackageStatus.DELIVERED);
        }

        log.info("✅ Payment sauvé avec nom/prénom : {}", saved);
        return saved;
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByUserId(String userId) {
        return paymentRepository.findByUserId(userId);
    }
}
