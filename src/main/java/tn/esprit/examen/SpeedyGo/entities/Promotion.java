package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document(collection = "promotion") // MongoDB Collection Name
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Promotion {


    @Id
    private String id;

    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private DiscountType discountType;
<<<<<<< HEAD
    private float discount;
    private PromotionStatus promotionStatus;
=======
    private double discount;
    private PromotionStatus promotionStatus;

>>>>>>> origin/main
}
