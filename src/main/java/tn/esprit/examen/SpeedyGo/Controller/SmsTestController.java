package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.SmsService;

@RestController
@RequestMapping("/api/sms-test")
@RequiredArgsConstructor
public class SmsTestController {

    private final SmsService smsService;

    @PostMapping
    public ResponseEntity<String> sendTestSms(@RequestParam String phone, @RequestParam String text) {
        try {
            smsService.sendSms(phone, text);
            return ResponseEntity.ok("✅ SMS de test envoyé à " + phone);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Échec d'envoi : " + e.getMessage());
        }
    }
}
