package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "leaves") // MongoDB collection name
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Leave {

    @Id
    private String id; // String ID (MongoDB ObjectId)

    private Date startDate;
    private Date endDate;
    private String reason;
    private Status status;
    private int totalDaysTaken;
}
