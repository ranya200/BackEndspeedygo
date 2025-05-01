package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



<<<<<<< HEAD
@Document(collection = "orderItems")
=======
@Document(collection = "orderItems") // Collection name in MongoDB
>>>>>>> origin/main
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
<<<<<<< HEAD
public class OrderItem {
    @Id
    private String productId;

    private String productName; // ✅ nouveau champ
    private float unitPrice;    // ✅ renommé
    private int quantity;
}

=======

public class OrderItem {
    @Id
    private String productId;
    private int quantity;
    private float price;
}
>>>>>>> origin/main
