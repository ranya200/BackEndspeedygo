package tn.esprit.examen.SpeedyGo.entities;

import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    private String id;

    private String rideId;
    private String passengerId;
    private int seatsBooked;
    private String status; // PENDING / CONFIRMED / CANCELED
}
