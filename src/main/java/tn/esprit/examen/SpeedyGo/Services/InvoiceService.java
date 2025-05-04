package tn.esprit.examen.SpeedyGo.Services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.GrayColor;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.entities.Payment;
import tn.esprit.examen.SpeedyGo.entities.User;
import com.itextpdf.text.BaseColor; // Import correct pour BaseColor

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

            String filePath = INVOICE_FOLDER + "Facture_de_confirmation_de_payment-" + payment.getId() + ".pdf";
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // ✅ Logo
            Image logo = Image.getInstance("https://raw.githubusercontent.com/ranya200/Frontspeedygo/refs/heads/main/src/assets/logo-color.png");
            logo.scaleToFit(120, 120);
            logo.setAlignment(Element.ALIGN_LEFT);
            document.add(logo);

            // ✅ Titre de la facture
            Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            titleFont.setColor(0, 102, 204); // Couleur bleue
            Paragraph title = new Paragraph("Invoice SpeedyGo", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // ✅ Infos utilisateur avec une couleur de fond
            Font sectionFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            sectionFont.setColor(0, 51, 102); // Couleur foncée pour les sections
            document.add(new Paragraph("BILLING TO:", sectionFont));
            document.add(new Paragraph("Name : " + user.getFirstName() + " " + user.getLastName()));
            document.add(new Paragraph("Email : " + user.getEmail()));

            document.add(new Paragraph("\n"));

            // ✅ Détails de paiement avec une bordure
            Font detailFont = new Font(Font.HELVETICA, 12);
            document.add(new Paragraph("Payment Details:", sectionFont));
            document.add(new Paragraph("\n" + "Amount paid : " + payment.getAmount() + " €", detailFont));
            document.add(new Paragraph("Invoice number : " + payment.getId(), detailFont));
            document.add(new Paragraph("Payment date : " + new SimpleDateFormat("dd/MM/yyyy").format(payment.getPaymentDate() != null ? payment.getPaymentDate() : new Date()), detailFont));

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));
            






            // ✅ Ligne de séparation
            LineSeparator separator = new LineSeparator();
            separator.setLineColor(new GrayColor(0.75f)); // Gris clair pour la ligne de séparation
            document.add(separator);


            // ✅ Message de remerciement
            Font thankYouFont = new Font(Font.HELVETICA, 12, Font.ITALIC);
            thankYouFont.setColor(0, 102, 0); // Couleur verte pour la partie "Merci"
            document.add(new Paragraph("\n\n" + "Thank you for your trust.", thankYouFont));

            // ✅ Ajouter des couleurs au bas de la page
            document.add(new Paragraph("\n"));
            Paragraph footer = new Paragraph("SpeedyGo - Your Trusted Delivery Partner", new Font(Font.HELVETICA, 10, Font.NORMAL));
            footer.setAlignment(Element.ALIGN_CENTER);
            //footer.setBackgroundColor(new BaseColor(245, 245, 245)); // Couleur de fond pour le footer
            document.add(footer);

            document.close();

            log.info("✅ Facture PDF générée à : {}", filePath);
            return filePath;

        } catch (Exception e) {
            log.error("❌ Erreur génération facture : {}", e.getMessage());
            return null;
        }
    }
}
