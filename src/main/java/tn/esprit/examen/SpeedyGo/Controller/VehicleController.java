package tn.esprit.examen.SpeedyGo.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IVehicleService;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    IVehicleService vehicleService;
    @Autowired
    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @GetMapping("/retrieve-all-vehicles")
    public List<Vehicle> getVehicles() {
        List<Vehicle> listvehicles = vehicleService.getAllVehicles();
        return listvehicles;
    }
    @GetMapping("/retrieve-vehicle/{vehicle-id}")
    public Vehicle getVehicle(@PathVariable("vehicle-id") String VId) {
        Vehicle vehicle = vehicleService.getVehicle(VId) ;
        return vehicle;
    }
    @PostMapping("/add-vehicle")
    public Vehicle addVehicle(@RequestBody Vehicle v) {
        Vehicle vehicle = vehicleService.addVehicle(v);
        return vehicle;
    }
    @DeleteMapping("/remove-vehicle/{vehicle-id}")
    public void removeVehicle(@PathVariable("vehicle-id") String VId) {
        vehicleService.deleteVehicle(VId);
    }

    @PutMapping("/modify-vehicle")
    public Vehicle modifyVehicle(@RequestBody Vehicle v) {
        Vehicle vehicle = vehicleService.modifyVehicle(v);
        return vehicle;
    }
}
