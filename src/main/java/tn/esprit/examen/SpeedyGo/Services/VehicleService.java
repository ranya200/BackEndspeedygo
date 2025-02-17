package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.VehicleRepository;
import tn.esprit.examen.SpeedyGo.entities.FastPost;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService implements IVehicleService{
    VehicleRepository vehicleRepository;
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(String idV) {
        vehicleRepository.deleteById(idV);
    }

    public Vehicle modifyVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles;
    }
    public Vehicle getVehicle(String VId) {
        return vehicleRepository.findById(VId).get();
    }
}
