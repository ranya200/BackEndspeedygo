package tn.esprit.examen.SpeedyGo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fastPost") // MongoDB annotation
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FastPost {
    @Id
    private String idF;
    private String receiverName;
    private String receiverAddress;
    private int receiverTelNbr;
    private float packageWeight;
    private float packageSize;

}
