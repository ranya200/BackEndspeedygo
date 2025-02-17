package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products") // Defines the MongoDB collection
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    private String id; // MongoDB uses String ID (ObjectId)

    private String name;
    private String description;
    private float price;
    private int stockQuantity;
    private Category category;
    private String image;

}
