package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;

public interface IPanierService {

    Panier getPackageForUser(String username);

    // ✅ Add a product to the package
    Panier addProductToPackage(Product product, int quantity, String username);

    // ✅ Remove a product from the package
    Panier removeProductFromPackage(String productId, String username);
    Panier updateQuantity(String productId, int quantity, String username);
    void clearPackageForUser(String username) ;
    double calculateTotalWeight(Panier panier);
}
