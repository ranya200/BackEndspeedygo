package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IProductService;
import tn.esprit.examen.SpeedyGo.entities.Product;
import org.springframework.http.MediaType;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.examen.SpeedyGo.entities.ProductStatus;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    //@PostMapping("/addProduct")
    //public Product addProduct(@RequestBody Product p) {
    //    return productService.addProduct(p);
    //}
    @PostMapping(value = "/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(@RequestPart("product") Product p,
                              @RequestPart("image") MultipartFile imageFile,
                              @AuthenticationPrincipal Jwt jwt) throws IOException {
        // ✅ Récupérer le nom du partenaire depuis le token JWT
        String partnerName = jwt.getClaim("preferred_username");

        // ✅ Vérifier les rôles de l'utilisateur
        Object realmAccessObj = jwt.getClaim("realm_access"); // Récupérer realm_access comme un objet
        List<String> roles = List.of(); // Initialisation d'une liste vide

        if (realmAccessObj instanceof Map) {
            Map<String, Object> realmAccess = (Map<String, Object>) realmAccessObj;
            if (realmAccess.containsKey("roles")) {
                roles = (List<String>) realmAccess.get("roles");
            }
        }

        // ✅ Vérifie que l'utilisateur a bien le rôle "partner"
        if (!roles.contains("partner")) {
            throw new RuntimeException("Vous n'avez pas les permissions pour ajouter un produit !");
        }

        // ✅ Convertir le fichier image en Base64
        String imageBase64 = Base64.getEncoder().encodeToString(imageFile.getBytes());
        p.setImage(imageBase64);

        // ✅ Associer le partenaire qui ajoute le produit
        p.setPartnerName(partnerName);
        p.setStatus(ProductStatus.PENDING); // 🟡 Par défaut en attente

        return productService.addProduct(p);
    }


    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product p) {
        return productService.updateProduct(p);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
    }

    @GetMapping(value ="/getProduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public Product getProduct(@PathVariable("id") String id) {
        return productService.getProduct(id);
    }

    @GetMapping(value = "/listProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> listProducts() {
        return productService.listProducts();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/pending")
    public List<Product> getPendingProducts() {
        return productService.getPendingProducts();
    }

    @PutMapping("/approve/{id}")
    public Product approveProduct(@PathVariable String id) {
        return productService.approveProduct(id);
    }

    @PutMapping("/reject/{id}")
    public Product rejectProduct(@PathVariable String id) {
        return productService.rejectProduct(id);
    }

    @GetMapping(value = "/my-products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> getMyProducts(@AuthenticationPrincipal Jwt jwt) {
        String partnerName = jwt.getClaim("preferred_username");

        // ✅ Vérifie que l'utilisateur est un partenaire
        Object realmAccessObj = jwt.getClaim("realm_access");
        List<String> roles = List.of();

        if (realmAccessObj instanceof Map) {
            Map<String, Object> realmAccess = (Map<String, Object>) realmAccessObj;
            if (realmAccess.containsKey("roles")) {
                roles = (List<String>) realmAccess.get("roles");
            }
        }

        if (!roles.contains("partner")) {
            throw new RuntimeException("Vous n'avez pas les permissions pour consulter vos produits !");
        }

        return productService.getProductsForPartner(partnerName);
    }

}
