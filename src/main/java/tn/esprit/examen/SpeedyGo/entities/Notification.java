package tn.esprit.examen.SpeedyGo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "notification") // MongoDB annotation
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String id;
    private String title;
    private String message;
    private String type; //
    private Date date;

}

