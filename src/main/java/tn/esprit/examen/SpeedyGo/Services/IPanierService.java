package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Panier;
import tn.esprit.examen.SpeedyGo.entities.Product;

public interface IPanierService {

    Panier getPackage();

    // ✅ Add a product to the package
    Panier addProductToPackage(Product product);

    // ✅ Remove a product from the package
    Panier removeProductFromPackage(String productId);
}
