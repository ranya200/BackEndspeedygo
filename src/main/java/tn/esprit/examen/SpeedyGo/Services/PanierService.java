package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.PanierRepo;
import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;
<<<<<<< HEAD
import tn.esprit.examen.SpeedyGo.entities.ProductInPanier;
=======
>>>>>>> origin/main

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class PanierService implements IPanierService {
    PanierRepo panierRepo;

    @Override
<<<<<<< HEAD
    public Panier getPackageForUser(String username) {
        return panierRepo.findAll().stream()
                .filter(p -> p.getOwnerUsername().equals(username))
                .findFirst().orElse(null);
    }

    @Override
    public Panier addProductToPackage(Product product, int quantity, String username) {
        Panier panier = getPackageForUser(username);
        if (panier == null) {
            panier = new Panier();
            panier.setOwnerUsername(username);
            panier.setProducts(new ArrayList<>());
        }

        // Vérifier si le produit est déjà dans le panier
        boolean found = false;
        for (ProductInPanier pip : panier.getProducts()) {
            if (pip.getProduct().getId().equals(product.getId())) {
                pip.setQuantity(pip.getQuantity() + quantity); // ajouter à la quantité
                found = true;
                break;
            }
        }

        if (!found) {
            panier.getProducts().add(new ProductInPanier(product, quantity));
        }

        panier.setTotalPrice(calculateTotalPrice(panier));
        return panierRepo.save(panier);
    }

    @Override
    public Panier removeProductFromPackage(String productId, String username) {
        Panier panier = getPackageForUser(username);
        if (panier == null) return null;

        panier.getProducts().removeIf(pip -> pip.getProduct().getId().equals(productId));
        panier.setTotalPrice(calculateTotalPrice(panier));
        return panierRepo.save(panier);
    }

    @Override
    public Panier updateQuantity(String productId, int quantity, String username) {
        Panier panier = getPackageForUser(username);
        if (panier == null) return null;

        for (ProductInPanier pip : panier.getProducts()) {
            if (pip.getProduct().getId().equals(productId)) {
                pip.setQuantity(quantity);
                break;
            }
        }

        panier.setTotalPrice(calculateTotalPrice(panier));
        return panierRepo.save(panier);
    }

    private double calculateTotalPrice(Panier panier) {
        return panier.getProducts().stream()
                .mapToDouble(pip -> pip.getProduct().getPrice() * pip.getQuantity())
                .sum();
    }

    public double calculateTotalWeight(Panier panier) {
        return panier.getProducts().stream()
                .mapToDouble(p -> p.getProduct().getWeight() * p.getQuantity())
                .sum();
    }


    public void clearPackageForUser(String username) {
        Panier panier = getPackageForUser(username);
        if (panier != null) {
            panier.setProducts(new ArrayList<>());
            panier.setTotalPrice(0.0);
            panierRepo.save(panier);
        }
    }

=======
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
>>>>>>> origin/main
}
