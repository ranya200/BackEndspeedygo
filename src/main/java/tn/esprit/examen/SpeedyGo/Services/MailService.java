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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    public void sendPaymentConfirmation(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("MS_M1A57p@test-yxj6lj9xrm14do2r.mlsender.net");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("✅ Email envoyé avec succès à {}", to);
        } catch (MessagingException e) {
            log.error("❌ Erreur lors de l'envoi de l'email : {}", e.getMessage());
        }
    }

    public String getHtmlPaymentTemplate(User user, Payment payment) {
        try {
            // ✅ Lien de la facture
            String invoiceUrl = "http://localhost:8089/speedygo/api/invoices/download/" + payment.getId();
            String encodedUrl = URLEncoder.encode(invoiceUrl, StandardCharsets.UTF_8);

            // ✅ Générer le QR code depuis API
            String qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?data=" + encodedUrl + "&size=200x200";
            BufferedImage qrImage = ImageIO.read(new URL(qrCodeUrl));
            if (qrImage == null) {
                log.error("❌ Impossible de charger l'image du QR code depuis l'URL : {}", qrCodeUrl);
            } else {
                log.info("✅ Image QR code chargée avec succès.");
            }
            String base64Qr = "data:image/png;base64," + encodeImageToBase64(qrImage);

            // ✅ Charger le logo
            String logoUrl = "https://raw.githubusercontent.com/ranya200/Frontspeedygo/refs/heads/main/src/assets/logo-color.png";
            BufferedImage logoImage = ImageIO.read(new URL(logoUrl));
            if (logoImage == null) {
                log.error("❌ Impossible de charger le logo depuis l'URL : {}", logoUrl);
            } else {
                log.info("✅ Logo chargé avec succès.");
            }
            String base64Logo = "data:image/png;base64," + encodeImageToBase64(logoImage);

            // ✅ HTML avec base64
            return """
            <!DOCTYPE html>
            <html lang="fr">
            <head>
              <meta charset="UTF-8">
              <title>Confirmation de Paiement</title>
            </head>
            <body style="font-family: Arial, sans-serif; background-color: #f6f9fc; padding: 0; margin: 0;">
              <div style="max-width: 600px; margin: auto; background: white; border-radius: 10px; overflow: hidden; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                <div style="background-color: #007bff; color: white; padding: 20px; text-align: center;">
                  <img src="%s" alt="SpeedyGo Logo" style="width: 120px; display: block; margin: auto;">
                  <h2>Confirmation de Paiement</h2>
                </div>
                <div style="padding: 30px; text-align: center;">
                  <p>Bonjour <strong>%s</strong>,</p>
                  <p>Votre paiement de <strong>%.2f €</strong> a bien été reçu.</p>
                  <p>📎 Téléchargez votre facture :</p>
                  <a href="%s" style="display: inline-block; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; margin-top: 10px;">📥 Télécharger la facture</a>
                  <p style="margin-top: 20px;">📷 Ou scannez ce QR code :</p>
                  <img src="%s" alt="QR Code" style="margin-top: 10px; border: 1px solid #ccc; padding: 5px;">
                </div>
                <div style="background: #f0f0f0; text-align: center; padding: 15px; font-size: 12px; color: #888;">
                  SpeedyGo © 2025 – Tous droits réservés<br>
                  Contact : support@speedygo.tn
                </div>
              </div>
            </body>
            </html>
            """.formatted(base64Logo, user.getFirstName(), payment.getAmount(), invoiceUrl, base64Qr);

        } catch (Exception e) {
            log.error("❌ Erreur génération HTML email avec QR base64 : {}", e.getMessage(), e);
            return "<p>Erreur lors de la génération du contenu email.</p>";
        }
    }

    private String encodeImageToBase64(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            log.error("❌ Erreur encodage image en base64 : {}", e.getMessage(), e);
            return "";
        }
    }
}
