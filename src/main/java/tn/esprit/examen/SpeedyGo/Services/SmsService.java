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

    public void sendSms(String toPhoneNumber, String text) {
        TextMessage message = new TextMessage(senderName, toPhoneNumber, text);

        try {
            SmsSubmissionResponse response = vonageClient.getSmsClient().submitMessage(message);

            if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
                log.info("✅ SMS envoyé avec succès à {}", toPhoneNumber);
            } else {
                log.warn("⚠️ Échec d'envoi SMS à {} : {}", toPhoneNumber, response.getMessages().get(0).getErrorText());
            }
        } catch (Exception e) {
            log.error("❌ Exception lors de l'envoi du SMS : {}", e.getMessage());
        }
    }
}
