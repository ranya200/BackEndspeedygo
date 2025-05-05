package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leavestatics") // MongoDB collection name
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveStatistics {
    @Id
    private String id;
    private int totalDaysTaken;
}
