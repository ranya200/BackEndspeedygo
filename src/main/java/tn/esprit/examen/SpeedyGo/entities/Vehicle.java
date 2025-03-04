package tn.esprit.examen.SpeedyGo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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



}
