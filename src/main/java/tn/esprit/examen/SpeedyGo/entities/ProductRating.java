package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "productRatings")
@AllArgsConstructor
@NoArgsConstructor
public class ProductRating {
    @Id
    private String id;
    private String userId;
    private String productId;
    private int rating; // de 1 à 5
}