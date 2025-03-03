package tn.esprit.examen.SpeedyGo.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IVehicleService;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;
import tn.esprit.examen.SpeedyGo.entities.VehicleStatus;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = "http://localhost:4200")

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Vehicle addVehicle(@RequestBody Vehicle v) {
        Vehicle vehicle = vehicleService.addVehicle(v);
        return vehicle;
    }
    @DeleteMapping("/remove-vehicle/{vehicle-id}")
    public void removeVehicle(@PathVariable("vehicle-id") String VId) {
        vehicleService.deleteVehicle(VId);
    }

    @PutMapping("/modify-vehicle/{vehicle-id}")
    //public Vehicle modifyVehicle(@RequestBody Vehicle v) {
       // Vehicle vehicle = vehicleService.modifyVehicle(v);
       // return vehicle;
    //}
    public Vehicle updateVehicle(@PathVariable("vehicle-id") String idV, @RequestBody Vehicle updatedVehicle) {
        return vehicleService.modifyVehicle(idV, updatedVehicle);
    }


    @PutMapping("/updateStatus/{id}")
    public Vehicle updateVehicleStatus(@PathVariable String id, @RequestParam boolean approved) {
        return vehicleService.updateVehicleStatus(id, approved);
    }

}
