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
        // Recherche du produit existant dans la base de données
        Product existingProduct = productRepo.findById(p.getId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable pour mise à jour"));

        // Avant la mise à jour, vérifier les champs sensibles et la prédiction
        log.info("Mise à jour du produit : " + existingProduct.getName() + " avec statut : " + existingProduct.getStatus());

        // Mise à jour des champs du produit
        p.setPartnerName(existingProduct.getPartnerName());
        p.setPreviousSales(existingProduct.getPreviousSales());
        p.setStatus(ProductStatus.APPROVED);  // S'assurer que le statut est bien mis à jour

        // Log avant la sauvegarde du produit
        log.info("Produit avant sauvegarde : " + p.getName() + " avec statut : " + p.getStatus());

        // Sauvegarder les modifications du produit
        Product updatedProduct = productRepo.save(p);

        // Log après la mise à jour
        log.info("Produit mis à jour : " + updatedProduct.getName() + " avec statut : " + updatedProduct.getStatus());

        return updatedProduct;
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
