package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.ProductRepo;
import tn.esprit.examen.SpeedyGo.entities.Product;

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
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepo.findByCategory(category);
    }


}
