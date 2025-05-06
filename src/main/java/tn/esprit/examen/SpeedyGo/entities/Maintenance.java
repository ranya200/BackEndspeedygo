package tn.esprit.examen.SpeedyGo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Schema(description = "Entité de maintenance véhicule")
@Document(collection = "maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Maintenance {
    @Id
    private String id;

    private String vehicleId;            // identifiant du véhicule concerné
    private String description;          // ex: "Vidange", "Révision 10k km"

    private double triggerDistanceKm;    // déclenché à quel kilométrage (ex: 10000 km)
    private double currentDistanceKm;    // kilométrage au moment de la maintenance

    private boolean completed;           // statut effectué ou non
}
