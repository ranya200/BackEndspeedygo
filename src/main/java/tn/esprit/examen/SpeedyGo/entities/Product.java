package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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

    // my adds firas
    private String promotionId; // référence à une promotion (optionnel)
    @Transient
    private Float promoPrice;



}
