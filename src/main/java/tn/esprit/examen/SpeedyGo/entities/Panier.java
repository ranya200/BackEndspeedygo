package tn.esprit.examen.SpeedyGo.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
@Document(collection = "package") // Collection name in MongoDB
=======
@Document(collection = "panier") // Collection name in MongoDB
>>>>>>> origin/main
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Panier {

    @Id
    private String id;
<<<<<<< HEAD
    private String ownerUsername;
    private List<ProductInPanier> products = new ArrayList<>(); // ✅ Initialize list to avoid null issues
    private double totalPrice;
    private String userId; // UUID from Keycloak

=======

    private List<Product> products = new ArrayList<>(); // ✅ Initialize list to avoid null issues

    private double totalPrice;
>>>>>>> origin/main
}
