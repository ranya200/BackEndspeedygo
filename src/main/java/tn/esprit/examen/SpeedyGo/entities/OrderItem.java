package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "orderItems")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem {
    @Id
    private String productId;

    private String productName; // ✅ nouveau champ
    private float unitPrice;    // ✅ renommé
    private int quantity;
}

