package tn.esprit.examen.SpeedyGo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.examen.SpeedyGo.entities.User;

import java.util.Date;

@Document(collection = "vehicle") // MongoDB annotation
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    private String idV;
    private String brand;
    private String model;
    private String capacity;
    private String licensePlate;
    private String vin;

    private Date fabricationDate;
    private String fuelType;
    private String imageFileName;


    private VehicleStatus vehicleStatus;
    private Status vehicleStatusD;
    private VehicleType vehicleType;
<<<<<<< Updated upstream
=======
    private String adminName;

    private boolean available; // pour l'auto-assignation
    private String assignedToDriverId; // ID du livreur assigné
    private String driverFirstName;
    private String driverLastName;

    //private String userId;

    private User driver; // conducteur lié (non stocké en DB)
>>>>>>> Stashed changes



}
