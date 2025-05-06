package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.VehicleRepository;
import tn.esprit.examen.SpeedyGo.entities.FastPost;
import tn.esprit.examen.SpeedyGo.entities.Status;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;
import java.util.Optional;

=======
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.Repository.VehicleRepository;
import tn.esprit.examen.SpeedyGo.entities.FastPost;
import tn.esprit.examen.SpeedyGo.entities.Status;
import tn.esprit.examen.SpeedyGo.entities.User;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;
import java.util.List;
>>>>>>> origin/main
@Service
@AllArgsConstructor
public class VehicleService implements IVehicleService{
    VehicleRepository vehicleRepository;
<<<<<<< HEAD
    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        // Ensure that newly added vehicles have 'PENDING' approval status
        if (vehicle.getVehicleStatusD() == null) {
            vehicle.setVehicleStatusD(Status.PENDING);
        }
=======
    UserRepository userRepository;
    UserService userService;
    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        if (vehicle.getVehicleStatusD() == null) {
            vehicle.setVehicleStatusD(Status.APPROVED);
        }

        // ✅ Obtenir tous les utilisateurs via UserService
        List<User> freeDrivers = userService.getAllUsers().stream()
                .filter(u -> u.getRoles() != null && u.getRoles().contains("driver"))
                .filter(u -> u.getAssignedVehicleId() == null || u.getAssignedVehicleId().isEmpty())
                .toList();

        if (!freeDrivers.isEmpty()) {
            User selectedDriver = freeDrivers.get(0);

            vehicle.setAssignedToDriverId(selectedDriver.getId());
            vehicle.setDriverFirstName(selectedDriver.getFirstName());
            vehicle.setDriverLastName(selectedDriver.getLastName());

            Vehicle savedVehicle = vehicleRepository.save(vehicle);

            selectedDriver.setAssignedVehicleId(savedVehicle.getIdV());
            userRepository.save(selectedDriver);

            return savedVehicle;
        }

>>>>>>> origin/main
        return vehicleRepository.save(vehicle);
    }


<<<<<<< HEAD
=======



>>>>>>> origin/main
    public void deleteVehicle(String idV) {
        vehicleRepository.deleteById(idV);
    }

    //public Vehicle modifyVehicle(Vehicle vehicle) {
        //return vehicleRepository.save(vehicle);
    //}
    public Vehicle modifyVehicle(String idV, Vehicle updatedVehicle) {
        return vehicleRepository.findById(idV).map(vehicle -> {
            vehicle.setBrand(updatedVehicle.getBrand());
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setCapacity(updatedVehicle.getCapacity());
            vehicle.setLicensePlate(updatedVehicle.getLicensePlate());
            vehicle.setVin(updatedVehicle.getVin());
            vehicle.setFabricationDate(updatedVehicle.getFabricationDate());
            vehicle.setFuelType(updatedVehicle.getFuelType());
            vehicle.setVehicleStatus(updatedVehicle.getVehicleStatus());
            vehicle.setVehicleType(updatedVehicle.getVehicleType());
            vehicle.setVehicleStatusD(updatedVehicle.getVehicleStatusD());

            return vehicleRepository.save(vehicle);
        }).orElseThrow(() -> new RuntimeException("❌ Vehicle not found with ID: " + idV));
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
<<<<<<< HEAD
        return vehicles;
    }
=======

        // ✅ Ajouter les infos du driver si assigné
        vehicles.forEach(vehicle -> {
            if (vehicle.getAssignedToDriverId() != null) {
                userRepository.findById(vehicle.getAssignedToDriverId())
                        .ifPresent(vehicle::setDriver);
            }
        });

        return vehicles;
    }

>>>>>>> origin/main
    public Vehicle getVehicle(String VId) {
        return vehicleRepository.findById(VId).get();
    }
    //public Vehicle modifystatusVehicle(Vehicle v) {
       // if (v.getIdV() != null) {
         //   Optional<Vehicle> existingVehicle = vehicleRepository.findById(v.getIdV());
        //    if (existingVehicle.isPresent()) {
         //       Vehicle vehicleToUpdate = existingVehicle.get();
          //      vehicleToUpdate.setVehicleStatus(v.getVehicleStatus());
           //     // update other fields as necessary
           //     return vehicleRepository.save(vehicleToUpdate);
         //   }
       // }
      //  return null;
    //}
    @Override
    public Vehicle updateVehicleStatus(String id, boolean approved) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setVehicleStatusD(approved ? Status.APPROVED : Status.REJECTED);
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    @Override
    public void approveVehicle(String VId) {
        Vehicle vehicle = vehicleRepository.findById(VId).orElse(null); // Retrieve the vehicle by ID.findById(id).orElse(null);
        if (vehicle != null) {
            vehicle.setVehicleStatusD(Status.APPROVED);
            vehicleRepository.save(vehicle);
        }
    }

    @Override
    public void rejectVehicle(String VId) {
        Vehicle vehicle = vehicleRepository.findById(VId).orElse(null); // Retrieve the vehicle by ID.findById(id).orElse(null);
        if (vehicle != null) {
            vehicle.setVehicleStatusD(Status.REJECTED);
            vehicleRepository.save(vehicle);
        }
    }
    public List<Vehicle> searchVehicles(String brand) {
        return vehicleRepository.findByBrand(brand);
    }


}
