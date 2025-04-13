package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    private String id; // Keycloak User ID
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<String> roles;

    private boolean available; // pour savoir si le livreur est dispo
    private String assignedVehicleId;
    private int dailyDeliveriesCount = 0; // initialisé à 0






    public User(String id, String username, String email, String firstName, String lastName , String phoneNumber, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.available = true; // ✅ valeur par défaut
        this.assignedVehicleId = null; // ✅ par défaut aucun véhicule assigné
        this.dailyDeliveriesCount = 0;
    }

}
