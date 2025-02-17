package tn.esprit.examen.SpeedyGo.Services;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;

public interface IVehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    void deleteVehicle(String idV);
    Vehicle modifyVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicle(String VId);
}
