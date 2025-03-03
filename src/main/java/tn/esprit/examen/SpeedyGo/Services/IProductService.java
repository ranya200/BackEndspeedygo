package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product p);
    Product updateProduct(Product p);
    void deleteProduct(String id);
    Product getProduct(String id);
    List<Product> listProducts();
    List<Product> getProductsByCategory(String category);

}
