package tn.esprit.examen.SpeedyGo.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.PackageStatus;
import tn.esprit.examen.SpeedyGo.Repository.PaymentRepo;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements IPaymentService {

    private final PaymentRepo paymentRepository;
    // Optionnel : injection du OrderService si vous souhaitez mettre à jour le statut de la commande associée
    private final OrderService orderService;


    public Payment processPayment(Payment payment) {
        // Simuler le traitement du paiement (ici, on suppose un paiement réussi)
        log.info("Traitement du paiement de type {} pour un montant de {}",
                payment.getPaymentType(), payment.getAmount());

        log.info("Paiement de {} € pour l'utilisateur {} et le panier {}",
                payment.getAmount(), payment.getUserId(), payment.getPackageId());

        boolean paymentSuccess = true; // Simuler un paiement réussi

        // Mettre à jour la date de paiement et le statut (true = paiement réussi)
        payment.setPaymentDate(new Date());
        payment.setStatus(paymentSuccess);

        Payment savedPayment = paymentRepository.save(payment);

        // Optionnel : si le paiement est associé à une commande, vous pouvez mettre à jour le statut de cette commande.
        // Par exemple, si Payment possédait un champ orderId :
        if (paymentSuccess && payment.getOrderId() != null) {
            orderService.updateOrderStatus(payment.getOrderId(), PackageStatus.DELIVERED);
         }

        return savedPayment;
    }

    // Vous pouvez ajouter d'autres méthodes liées aux paiements (recherche, historique, etc.)
}
