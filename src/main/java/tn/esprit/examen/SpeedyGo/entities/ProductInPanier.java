package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInPanier {
    private Product product;
    private int quantity;
}
