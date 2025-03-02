package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users") // Defines the MongoDB collection
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
    private String role;
}
