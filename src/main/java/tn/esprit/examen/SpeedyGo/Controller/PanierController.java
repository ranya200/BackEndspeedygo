package tn.esprit.examen.SpeedyGo.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IPanierService;
import tn.esprit.examen.SpeedyGo.Services.PanierService;
import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/panier")
public class PanierController {

    private final IPanierService panierService;

    @PostMapping("/addProduct")
    public ResponseEntity<Panier> addProductToPackage(
            @RequestBody Map<String, Object> request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String username = jwt.getClaim("preferred_username");
        Product product = new Product();
        Map<String, Object> productMap = (Map<String, Object>) request.get("product");

        product.setId((String) productMap.get("id"));
        product.setName((String) productMap.get("name"));
        product.setDescription((String) productMap.get("description"));
        product.setPrice(Float.parseFloat(productMap.get("price").toString()));
        product.setImage((String) productMap.get("image"));
        // ... Ajouter les autres champs si besoin

        int quantity = (int)((Map<String, Object>) request.get("quantity")).get("value");

        return ResponseEntity.ok(panierService.addProductToPackage(product, quantity, username));
    }

    @DeleteMapping("/removeProduct/{productId}")
    public ResponseEntity<Panier> removeProductFromPackage(
            @PathVariable String productId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String username = jwt.getClaim("preferred_username");
        return ResponseEntity.ok(panierService.removeProductFromPackage(productId, username));
    }

    @GetMapping("/getPackage")
    public ResponseEntity<Panier> getPackage(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaim("preferred_username");
        return ResponseEntity.ok(panierService.getPackageForUser(username));
    }

    @PutMapping("/updateQuantity/{productId}")
    public ResponseEntity<Panier> updateProductQuantity(
            @PathVariable String productId,
            @RequestParam int quantity,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String username = jwt.getClaim("preferred_username");
        return ResponseEntity.ok(panierService.updateQuantity(productId, quantity, username));
    }

    @DeleteMapping("/clear/{username}")
    public ResponseEntity<Void> clearPackage(@PathVariable String username) {
        panierService.clearPackageForUser(username);
        return ResponseEntity.ok().build();
    }

}
