package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.PanierRepo;
import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class PanierService implements IPanierService {
    PanierRepo panierRepo;

    @Override
    public Panier getPackage() {
        return panierRepo.findAll().stream().findFirst().orElse(null);
    }

    // ✅ Add a product to the package
    @Override
    public Panier addProductToPackage(Product product) {
        Optional<Panier> optionalPackage = panierRepo.findAll().stream().findFirst();
        Panier packageEntity = optionalPackage.orElseGet(() -> {
            Panier newPackage = new Panier();
            newPackage.setProducts(new ArrayList<>()); // ✅ Ensure products list is initialized
            return newPackage;
        });

        packageEntity.getProducts().add(product);
        packageEntity.setTotalPrice(calculateTotalPrice(packageEntity));

        return panierRepo.save(packageEntity);
    }

    // ✅ Remove a product from the package
    @Override
    public Panier removeProductFromPackage(String productId) {
        Optional<Panier> optionalPackage = panierRepo.findAll().stream().findFirst();

        if (optionalPackage.isPresent()) {
            Panier packageEntity = optionalPackage.get();
            packageEntity.setProducts(packageEntity.getProducts().stream()
                    .filter(product -> !product.getId().equals(productId))
                    .toList());

            packageEntity.setTotalPrice(calculateTotalPrice(packageEntity));
            return panierRepo.save(packageEntity);
        }
        return null;
    }

    // ✅ Calculate total price of all products in package
    private double calculateTotalPrice(Panier packageEntity) {
        return packageEntity.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
