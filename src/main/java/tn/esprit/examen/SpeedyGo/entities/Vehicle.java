package tn.esprit.examen.SpeedyGo.entities;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonFormat;
>>>>>>> origin/main
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
<<<<<<< HEAD
import org.springframework.data.mongodb.core.mapping.Document;
=======
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.examen.SpeedyGo.entities.User;
>>>>>>> origin/main

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
<<<<<<< HEAD


=======
>>>>>>> origin/main
    private VehicleStatus vehicleStatus;
    private Status vehicleStatusD;
    private VehicleType vehicleType;
    private String adminName;
<<<<<<< HEAD
=======
    private boolean available; // pour l'auto-assignation
    private String assignedToDriverId; // ID du livreur assigné
    private String driverFirstName;
    private String driverLastName;
    private User driver; // conducteur lié (non stocké en DB)
>>>>>>> origin/main




}
