package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "orderItems") // Collection name in MongoDB
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderItem {
    @Id
    private String productId;
    private int quantity;
    private float price;
}
