package tn.esprit.examen.SpeedyGo.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IPanierService;
import tn.esprit.examen.SpeedyGo.Services.PanierService;
import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/panier")
public class PanierController {

    private final IPanierService panierService;

    @PostMapping("/addProduct")
    public ResponseEntity<Panier> addProductToPackage(@RequestBody Product product) {
        Panier updatedPackage = panierService.addProductToPackage(product);
        return ResponseEntity.ok(updatedPackage);
    }

    @DeleteMapping("/removeProduct/{productId}")
    public ResponseEntity<Panier> removeProductFromPackage(@PathVariable String productId) {
        Panier updatedPackage = panierService.removeProductFromPackage(productId);
        return ResponseEntity.ok(updatedPackage);
    }

    @GetMapping("/getPackage")
    public ResponseEntity<Panier> getPackage() {
        return ResponseEntity.ok(panierService.getPackage());
    }

}
