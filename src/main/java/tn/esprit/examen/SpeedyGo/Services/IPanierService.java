package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;

public interface IPanierService {

<<<<<<< HEAD
    Panier getPackageForUser(String username);

    // ✅ Add a product to the package
    Panier addProductToPackage(Product product, int quantity, String username);

    // ✅ Remove a product from the package
    Panier removeProductFromPackage(String productId, String username);
    Panier updateQuantity(String productId, int quantity, String username);
    void clearPackageForUser(String username) ;
    double calculateTotalWeight(Panier panier);
=======
    Panier getPackage();

    // ✅ Add a product to the package
    Panier addProductToPackage(Product product);

    // ✅ Remove a product from the package
    Panier removeProductFromPackage(String productId);
>>>>>>> origin/main
}
