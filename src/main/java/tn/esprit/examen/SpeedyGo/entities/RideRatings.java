package tn.esprit.examen.SpeedyGo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ride_ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideRatings {
    @Id
    private String id;

    private String rideId; // The ride being rated
    private String driverId; // The driver being rated
    private String passengerId; // Who submitted the rating

    private int safetyScore;       // 1–5
    private int punctualityScore;  // 1–5
    private int comfortScore;      // 1–5

    private String comment; // optional

    private LocalDateTime createdAt;
}
