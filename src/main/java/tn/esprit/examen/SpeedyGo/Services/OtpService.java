package tn.esprit.examen.SpeedyGo.Services;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value; // ✅ Correct
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.service.sid}")
    private String serviceSid;

    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.load();
        accountSid = dotenv.get("TWILIO_ACCOUNT_SID");
        authToken = dotenv.get("TWILIO_AUTH_TOKEN");
        serviceSid = dotenv.get("TWILIO_SERVICE_SID");

        Twilio.init(accountSid, authToken);
    }


    public void sendOtp(String phoneNumber) {
        Verification.creator(serviceSid, phoneNumber, "sms").create();
    }

    public boolean verifyOtp(String phoneNumber, String code) {
        VerificationCheck verificationCheck = VerificationCheck.creator(serviceSid)
                .setTo(phoneNumber)
                .setCode(code)
                .create();
        return "approved".equals(verificationCheck.getStatus());
    }
}
