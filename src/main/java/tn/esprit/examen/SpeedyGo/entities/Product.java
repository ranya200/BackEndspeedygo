package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> origin/main

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
    private String partnerName; // ✅ Nouveau champ pour enregistrer le nom du partenaire
<<<<<<< HEAD
    private double weight; // en kg par exemple
    private ProductStatus status;
    private int previousSales = 0;
    private String prediction;
=======

    // my adds firas
    private double discountedPrice;
    private Promotion promotion;
>>>>>>> origin/main

}
