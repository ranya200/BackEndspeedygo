package tn.esprit.examen.SpeedyGo.Controller;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vehicle")
@CrossOrigin(origins = "http://localhost:4200")

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
    }


    @PostMapping(value = "/add-vehicle", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
        if (!roles.contains("ADMIN")) {
            throw new RuntimeException("Vous n'avez pas les permissions pour ajouter un véhicule !");
        }

        // ✅ Convertir le fichier image en Base64
        String imageBase64 = Base64.getEncoder().encodeToString(imageFileName.getBytes());
        v.setImageFileName(imageBase64);

        // ✅ Associer l'admin qui ajoute le véhicule
        v.setAdminName(adminName);

        return vehicleService.addVehicle(v);
    }

    @DeleteMapping("/remove-vehicle/{vehicle-id}")
    public void removeVehicle(@PathVariable("vehicle-id") String VId) {
        vehicleService.deleteVehicle(VId);
    }

    @PutMapping("/modify-vehicle/{vehicle-id}")
    public Vehicle updateVehicle(@PathVariable("vehicle-id") String idV, @RequestBody Vehicle updatedVehicle) {
        return vehicleService.modifyVehicle(idV, updatedVehicle);
    }


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

    @GetMapping(value = "/search", produces = "application/json") // Ensure JSON response
    public ResponseEntity<List<Vehicle>> searchVehicles(@RequestParam String brand) {
        List<Vehicle> vehicles = vehicleService.searchVehicles(brand);
        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vehicles);
    }
}
