package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "payments") // Collection name in MongoDB
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    @Id
    private String id; // MongoDB default String ID

    private float amount;
    private PaymentType paymentType;
    private Date paymentDate;
    private boolean status;
    // Lien avec la commande
    private String orderId;
}
