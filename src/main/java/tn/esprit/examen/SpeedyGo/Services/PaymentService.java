package tn.esprit.examen.SpeedyGo.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;
import tn.esprit.examen.SpeedyGo.Repository.PaymentRepo;
import tn.esprit.examen.SpeedyGo.entities.User;

import java.util.Date;
import java.util.List;
=======
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;
import tn.esprit.examen.SpeedyGo.Repository.PaymentRepo;

import java.util.Date;
>>>>>>> origin/main

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements IPaymentService {

    private final PaymentRepo paymentRepository;
<<<<<<< HEAD
    private final OrderService orderService;

    @Autowired
    private MailService mailService;


    @Autowired
    private UserRepository userRepository;

    public Payment processPayment(Payment payment) {
        log.info("Traitement du paiement de type {} pour un montant de {}", payment.getPaymentType(), payment.getAmount());
        log.info("Paiement de {} € pour l'utilisateur {} et le panier {}", payment.getAmount(), payment.getUserId(), payment.getPackageId());

        boolean paymentSuccess = true;

=======
    // Optionnel : injection du OrderService si vous souhaitez mettre à jour le statut de la commande associée
    private final OrderService orderService;


    public Payment processPayment(Payment payment) {
        // Simuler le traitement du paiement (ici, on suppose un paiement réussi)
        log.info("Traitement du paiement de type {} pour un montant de {}",
                payment.getPaymentType(), payment.getAmount());

        boolean paymentSuccess = true; // Simuler un paiement réussi

        // Mettre à jour la date de paiement et le statut (true = paiement réussi)
>>>>>>> origin/main
        payment.setPaymentDate(new Date());
        payment.setStatus(paymentSuccess);

        Payment savedPayment = paymentRepository.save(payment);

<<<<<<< HEAD
        if (paymentSuccess && payment.getOrderId() != null) {
            orderService.updateOrderStatus(payment.getOrderId(), PackageStatus.DELIVERED);
        }
=======
        // Optionnel : si le paiement est associé à une commande, vous pouvez mettre à jour le statut de cette commande.
        // Par exemple, si Payment possédait un champ orderId :
        if (paymentSuccess && payment.getOrderId() != null) {
            orderService.updateOrderStatus(payment.getOrderId(), PackageStatus.DELIVERED);
         }
>>>>>>> origin/main

        return savedPayment;
    }

<<<<<<< HEAD
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
=======
    // Vous pouvez ajouter d'autres méthodes liées aux paiements (recherche, historique, etc.)
>>>>>>> origin/main
}
