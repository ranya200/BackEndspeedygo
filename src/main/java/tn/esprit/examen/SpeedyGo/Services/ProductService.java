package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.ProductRepo;
import tn.esprit.examen.SpeedyGo.entities.Product;
import tn.esprit.examen.SpeedyGo.entities.ProductStatus;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductService {
    ProductRepo productRepo;


    @Override
    public Product addProduct(Product p) {
        return productRepo.save(p);
    }

    @Override
    public Product updateProduct(Product p) {
        return productRepo.save(p);
    }

    @Override
    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getProduct(String id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public List<Product> listProducts() {
        return productRepo.findByStatus(ProductStatus.APPROVED);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepo.findByCategory(category);
    }

    @Override
    public List<Product> getPendingProducts() {
        return productRepo.findByStatus(ProductStatus.PENDING);
    }

    @Override
    public Product approveProduct(String id) {
        Product p = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        p.setStatus(ProductStatus.APPROVED);
        return productRepo.save(p);
    }

    @Override
    public Product rejectProduct(String id) {
        Product p = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        p.setStatus(ProductStatus.REJECTED);
        return productRepo.save(p);
    }

    @Override
    public List<Product> getProductsForPartner(String partnerName) {
        return productRepo.findByPartnerNameAndStatus(partnerName, ProductStatus.APPROVED);
    }

}
