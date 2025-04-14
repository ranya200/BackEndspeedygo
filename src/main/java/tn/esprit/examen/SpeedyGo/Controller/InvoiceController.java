package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Repository.PaymentRepo;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.Services.InvoiceService;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.User;

import java.io.File;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final PaymentRepo paymentRepo;
    private final UserRepository userRepository;

    @GetMapping("/download/{paymentId}")
    public ResponseEntity<Resource> downloadInvoice(@PathVariable String paymentId) {
        Optional<Payment> paymentOpt = paymentRepo.findById(paymentId);
        if (paymentOpt.isEmpty()) return ResponseEntity.notFound().build();

        Payment payment = paymentOpt.get();
        Optional<User> userOpt = userRepository.findById(payment.getUserId());
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        String path = invoiceService.generateInvoicePdf(payment, userOpt.get());
        if (path == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        File file = new File(path);
        if (!file.exists()) return ResponseEntity.notFound().build();

        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .body(resource);
    }
}