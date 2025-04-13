package tn.esprit.examen.SpeedyGo.Services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.User;

import java.io.FileOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class InvoiceService {

    private static final String INVOICE_FOLDER = "invoices/";

    public String generateInvoicePdf(Payment payment, User user) {
        try {
            File dir = new File(INVOICE_FOLDER);
            if (!dir.exists()) dir.mkdirs();

            String filePath = INVOICE_FOLDER + "invoice-" + payment.getId() + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // ✅ Logo
            Image logo = Image.getInstance("https://raw.githubusercontent.com/ranya200/Frontspeedygo/refs/heads/main/src/assets/logo-color.png");
            logo.scaleToFit(100, 100);
            document.add(logo);

            // ✅ Titre
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Facture SpeedyGo", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("\n"));

            // ✅ Infos utilisateur
            document.add(new Paragraph("Nom : " + user.getFirstName() + " " + user.getLastName()));
            document.add(new Paragraph("Email : " + user.getEmail()));
            document.add(new Paragraph("Montant payé : " + payment.getAmount() + " €"));
            document.add(new Paragraph("Numéro de facture : " + payment.getId()));
            document.add(new Paragraph("Date de paiement : " +
                    new SimpleDateFormat("dd/MM/yyyy").format(payment.getPaymentDate() != null ? payment.getPaymentDate() : new Date())));

            document.add(new Paragraph("\nMerci pour votre confiance.", new Font(Font.HELVETICA, 12, Font.ITALIC)));
            document.close();

            log.info("✅ Facture PDF OpenPDF générée à : {}", filePath);
            return filePath;

        } catch (Exception e) {
            log.error("❌ Erreur génération facture OpenPDF : {}", e.getMessage());
            return null;
        }
    }
}
