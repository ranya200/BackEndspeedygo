package tn.esprit.examen.SpeedyGo.entities;




import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "carpooling")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carpooling {

    @Id
    private String id;

    private String driverName;
    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime departureTime;
    private int availableSeats;
    private double pricePerSeat;

    private Services typeservice;

}
