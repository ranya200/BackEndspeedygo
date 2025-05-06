package tn.esprit.examen.SpeedyGo.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "rideRequests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RideRequest {
    @Id
    private String id;

    private String passengerId;
    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime preferredTime;
    private int seatsNeeded;
    private String status; // PENDING / ACCEPTED / REJECTED

    private List<String> appliedDriverIds = new ArrayList<>(); // drivers who applied
    private String confirmedDriverId; // passenger picks one
}
