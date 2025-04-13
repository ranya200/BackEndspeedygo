package tn.esprit.examen.SpeedyGo.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    public void sendPaymentConfirmation(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("MS_M1A57p@test-yxj6lj9xrm14do2r.mlsender.net"); // Adresse utilisée chez MailerSend
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = HTML

            mailSender.send(message);
            log.info("✅ Email envoyé avec succès à {}", to);
        } catch (MessagingException e) {
            log.error("❌ Erreur lors de l'envoi de l'email : {}", e.getMessage());
        }
    }

    public String getHtmlPaymentTemplate(User user, Payment payment) {
        return """
        <html>
          <body style='font-family:Arial,sans-serif;'>
            <h2>Bonjour %s 👋</h2>
            <p>Merci pour votre paiement de <strong>%.2f €</strong>.</p>
            <p>Votre commande est en cours de traitement.</p>
            <br><p>— L’équipe SpeedyGo 🚀</p>
          </body>
        </html>
        """.formatted(user.getFirstName(), payment.getAmount());
    }
}
