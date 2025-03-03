package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IProductService;
import tn.esprit.examen.SpeedyGo.entities.Product;
import org.springframework.http.MediaType;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;

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
    @PostMapping(value="/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(@RequestPart("product") Product p, @RequestPart("image") MultipartFile imageFile) throws IOException {
        // Convertir le fichier image en Base64
        String imageBase64 = Base64.getEncoder().encodeToString(imageFile.getBytes());
        p.setImage(imageBase64);
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
}
