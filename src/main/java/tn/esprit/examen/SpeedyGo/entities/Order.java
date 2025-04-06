package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "orders") // Collection name in MongoDB
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    private String id; // MongoDB default String ID

    private String trackingNumber;
    private Date estimatedDeliveryDate;
    private float price;
    private Priority priority;
    private PackageStatus status;
    private Date date;

    // Nouvel attribut pour les produits commandés
    private List<OrderItem> items;

    private String userId; // ✅ Lien avec l'utilisateur

}
