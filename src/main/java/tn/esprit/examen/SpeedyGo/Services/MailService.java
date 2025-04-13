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
        String invoiceUrl = "http://localhost:8089/speedygo/api/invoices/download/" + payment.getId();
        String qrCode = "https://api.qrserver.com/v1/create-qr-code/?data=" + invoiceUrl + "&size=150x150";

        return """
    <!DOCTYPE html>
    <html lang="fr">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Confirmation de Paiement</title>
      <style>
        body { font-family: 'Arial', sans-serif; background-color: #f6f9fc; margin: 0; padding: 0; }
        .email-container { max-width: 600px; margin: auto; background: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.05); overflow: hidden; }
        .email-header { background-color: #0066cc; color: white; padding: 20px; text-align: center; }
        .email-body { padding: 30px; color: #333333; }
        .email-footer { padding: 20px; text-align: center; font-size: 12px; color: #999999; }
        .btn { display: inline-block; margin-top: 20px; padding: 12px 24px; background-color: #0066cc; color: white; text-decoration: none; border-radius: 6px; }
        img.logo { width: 120px; margin-bottom: 10px; }
      </style>
    </head>
    <body>
      <div class="email-container">
        <div class="email-header">
          <img src="https://raw.githubusercontent.com/ranya200/Frontspeedygo/refs/heads/main/src/assets/logo-color.png" alt="SpeedyGo Logo" class="logo">
          <h2>Confirmation de Paiement</h2>
        </div>
        <div class="email-body">
          <p>Bonjour <strong>%s</strong>,</p>
          <p>Votre paiement de <strong>%.2f €</strong> a bien été reçu.</p>
          <p>Vous pouvez télécharger votre facture ici :</p>
          <a class="btn" href="%s">📥 Télécharger la facture</a>
          <p>Ou scanner ce QR code :</p>
          <img src="%s" alt="QR Code">
        </div>
        <div class="email-footer">
          SpeedyGo © 2025 – Tous droits réservés<br>
          Contact : support@speedygo.tn
        </div>
      </div>
    </body>
    </html>
    """.formatted(user.getFirstName(), payment.getAmount(), invoiceUrl, qrCode);
    }


}
