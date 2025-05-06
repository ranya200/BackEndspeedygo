package tn.esprit.examen.SpeedyGo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

@Document(collection = "delivery") // MongoDB annotation
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    private String idD;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime estimatedDeliveryTime;
    private PamentStatus pamentStatus;
    private Status status;
    private String driverId;
    private String userId;
<<<<<<< HEAD
=======
    private String driverFirstName;
    private String driverLastName;
>>>>>>> origin/main
}
