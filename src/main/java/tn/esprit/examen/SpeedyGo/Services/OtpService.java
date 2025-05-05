package tn.esprit.examen.SpeedyGo.Services;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
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
