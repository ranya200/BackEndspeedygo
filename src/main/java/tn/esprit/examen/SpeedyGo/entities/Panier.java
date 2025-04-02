package tn.esprit.examen.SpeedyGo.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "package") // Collection name in MongoDB
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Panier {

    @Id
    private String id;
    private String ownerUsername;
    private List<ProductInPanier> products = new ArrayList<>(); // ✅ Initialize list to avoid null issues
    private double totalPrice;

}
