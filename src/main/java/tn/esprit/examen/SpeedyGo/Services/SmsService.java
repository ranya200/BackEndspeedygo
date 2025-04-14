package tn.esprit.examen.SpeedyGo.Services;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {

    @Value("${vonage.api.key}")
    private String apiKey;

    @Value("${vonage.api.secret}")
    private String apiSecret;

    @Value("${vonage.sender.name}")
    private String senderName;

    private VonageClient vonageClient;

    @PostConstruct
    public void init() {
        vonageClient = VonageClient.builder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build();
    }

    public void sendSms(String rawPhoneNumber, String text) {
        String formattedNumber = formatPhoneNumber(rawPhoneNumber);
        log.info("📲 Envoi SMS à {}: {}", formattedNumber, text);

        TextMessage message = new TextMessage(senderName, formattedNumber, text);

        try {
            SmsSubmissionResponse response = vonageClient.getSmsClient().submitMessage(message);

            if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                log.info("✅ SMS envoyé avec succès à {}", formattedNumber);
            } else {
                log.warn("⚠️ Échec d'envoi SMS à {} : {}", formattedNumber, response.getMessages().get(0).getErrorText());
            }
        } catch (Exception e) {
            log.error("❌ Exception lors de l'envoi du SMS à {} : {}", formattedNumber, e.getMessage());
        }
    }

    private String formatPhoneNumber(String raw) {
        if (raw == null || raw.isBlank()) return "";

        // Supprime les espaces ou caractères spéciaux
        String cleaned = raw.replaceAll("[^\\d]", "");

        // Ajoute l'indicatif tunisien si manquant
        if (cleaned.startsWith("216")) {
            return "+" + cleaned;
        } else if (cleaned.length() == 8) {
            return "+216" + cleaned;
        } else {
            // Cas inattendu
            log.warn("📵 Format de numéro inattendu : {}", raw);
            return "+" + cleaned; // fallback
        }
    }
}
