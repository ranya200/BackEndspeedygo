package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.ProductRepo;
import tn.esprit.examen.SpeedyGo.Repository.PromotionRepository;
import tn.esprit.examen.SpeedyGo.entities.Product;
import tn.esprit.examen.SpeedyGo.entities.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductService {
    ProductRepo productRepo;
    PromotionRepository promotionRepository;


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

    //firas
    @Override
    public float calculateFinalPrice(Product product) {
        Promotion promo = promotionRepository.findActivePromotionByProductId(product.getId(), LocalDate.now());
        if (promo == null) {
            return product.getPrice();
        }

        return switch (promo.getType()) {
            case POURCENTAGE -> product.getPrice() * (1 - promo.getValue() / 100);
            case FIXEDAMOUNT -> Math.max(0, product.getPrice() - promo.getValue());
            case FREEDELIVERY -> product.getPrice(); // Tu peux gérer la livraison gratuite ailleurs
        };
    }

    public List<Product> getAllProductsWithPromotions() {
        List<Product> products = productRepo.findAll();

        for (Product product : products) {
            Optional<Promotion> activePromo = promotionRepository.findActivePromotionForProduct(product.getId(), LocalDate.now());
            if (activePromo.isPresent()) {
                Promotion promo = activePromo.get();
                float originalPrice = product.getPrice();
                float newPrice = originalPrice;

                switch (promo.getType()) {
                    case POURCENTAGE:
                        newPrice = originalPrice * (1 - promo.getValue() / 100);
                        break;
                    case FIXEDAMOUNT:
                        newPrice = originalPrice - promo.getValue();
                        break;
                    case FREEDELIVERY:
                        // Le prix ne change pas mais tu peux ajouter un flag si besoin
                        break;
                }

                // Ajoute le champ promotionnel au produit
                product.setPromoPrice(Math.max(newPrice, 0)); // Ajoute ce champ dans ton entité `Product`
            }
        }

        return products;
    }


}
