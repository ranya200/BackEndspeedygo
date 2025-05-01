package tn.esprit.examen.SpeedyGo.Controller;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.examen.SpeedyGo.Services.IVehicleService;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.Map;

=======

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.examen.SpeedyGo.Repository.MaintenanceRepository;
import tn.esprit.examen.SpeedyGo.Services.IVehicleMaintenanceService;
import tn.esprit.examen.SpeedyGo.Services.IVehicleService;
import tn.esprit.examen.SpeedyGo.entities.Maintenance;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;


>>>>>>> origin/main
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = "http://localhost:4200")
<<<<<<< HEAD

public class VehicleController {
    IVehicleService vehicleService;
    private Vehicle v;
    private MultipartFile imageFile;

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
=======
public class VehicleController {

    private final IVehicleService vehicleService;

    // facultatif (non utilisé)
    private Vehicle v;
    private MultipartFile imageFile;

    @GetMapping("/retrieve-all-vehicles")
    public List<Vehicle> getVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/retrieve-vehicle/{vehicle-id}")
    public Vehicle getVehicle(@PathVariable("vehicle-id") String VId) {
        return vehicleService.getVehicle(VId);
>>>>>>> origin/main
    }


    @PostMapping(value = "/add-vehicle", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
<<<<<<< HEAD
    public Vehicle addVehicle(@RequestPart("vehicle") Vehicle v,
                              @RequestPart("imageFileName") MultipartFile imageFileName,
                              @AuthenticationPrincipal Jwt jwt) throws IOException {

        // ✅ Récupérer le nom de l'admin depuis le token JWT
        String adminName = jwt.getClaim("preferred_username");

        // ✅ Vérifier les rôles de l'utilisateur
        Object realmAccessObj = jwt.getClaim("realm_access");
        List<String> roles = List.of();

        if (realmAccessObj instanceof Map) {
            Map<String, Object> realmAccess = (Map<String, Object>) realmAccessObj;
            if (realmAccess.containsKey("roles")) {
                roles = (List<String>) realmAccess.get("roles");
            }
        }

        // ✅ Vérifie que l'utilisateur a bien le rôle "admin"
=======
    public Vehicle addVehicle(
            @RequestPart("vehicle") Vehicle vehicle,
            @RequestPart("imageFileName") MultipartFile imageFileName,
            @AuthenticationPrincipal Jwt jwt) throws IOException {

        // ✅ Récupérer le nom d'utilisateur
        String adminName = jwt.getClaim("preferred_username");

        // ✅ Vérifier rôle ADMIN
        Object realmAccessObj = jwt.getClaim("realm_access");
        List<String> roles = List.of();
        if (realmAccessObj instanceof Map<?, ?> realmAccess) {
            Object rolesObj = realmAccess.get("roles");
            if (rolesObj instanceof List<?> roleList) {
                roles = (List<String>) roleList;
            }
        }

>>>>>>> origin/main
        if (!roles.contains("ADMIN")) {
            throw new RuntimeException("Vous n'avez pas les permissions pour ajouter un véhicule !");
        }

<<<<<<< HEAD
        // ✅ Convertir le fichier image en Base64
        String imageBase64 = Base64.getEncoder().encodeToString(imageFileName.getBytes());
        v.setImageFileName(imageBase64);

        // ✅ Associer l'admin qui ajoute le véhicule
        v.setAdminName(adminName);

        return vehicleService.addVehicle(v);
=======
        // ✅ Traiter l’image
        String imageBase64 = Base64.getEncoder().encodeToString(imageFileName.getBytes());
        vehicle.setImageFileName(imageBase64);
        vehicle.setAdminName(adminName);

        return vehicleService.addVehicle(vehicle);
>>>>>>> origin/main
    }

    @DeleteMapping("/remove-vehicle/{vehicle-id}")
    public void removeVehicle(@PathVariable("vehicle-id") String VId) {
        vehicleService.deleteVehicle(VId);
    }

    @PutMapping("/modify-vehicle/{vehicle-id}")
<<<<<<< HEAD
    public Vehicle updateVehicle(@PathVariable("vehicle-id") String idV, @RequestBody Vehicle updatedVehicle) {
        return vehicleService.modifyVehicle(idV, updatedVehicle);
    }


=======
    public Vehicle updateVehicle(@PathVariable("vehicle-id") String idV,
                                 @RequestBody Vehicle updatedVehicle) {
        return vehicleService.modifyVehicle(idV, updatedVehicle);
    }

>>>>>>> origin/main
    @PutMapping("/updateStatus/{id}")
    public Vehicle updateVehicleStatus(@PathVariable String id, @RequestParam boolean approved) {
        return vehicleService.updateVehicleStatus(id, approved);
    }

    @PutMapping("/approve/{id}")
    public void approveVehicle(@PathVariable String id) {
        vehicleService.approveVehicle(id);
    }

    @PutMapping("/reject/{id}")
    public void rejectVehicle(@PathVariable String id) {
        vehicleService.rejectVehicle(id);
    }

<<<<<<< HEAD
    @GetMapping(value = "/search", produces = "application/json") // Ensure JSON response
=======





    // ✅ Recherche par marque
    @GetMapping(value = "/search", produces = "application/json")
>>>>>>> origin/main
    public ResponseEntity<List<Vehicle>> searchVehicles(@RequestParam String brand) {
        List<Vehicle> vehicles = vehicleService.searchVehicles(brand);
        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehicles);
    }
<<<<<<< HEAD
=======


>>>>>>> origin/main
}
