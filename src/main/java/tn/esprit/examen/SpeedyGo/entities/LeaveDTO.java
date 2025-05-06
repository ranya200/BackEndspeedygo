package tn.esprit.examen.SpeedyGo.entities;
import tn.esprit.examen.SpeedyGo.entities.Status;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDTO {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private Status status;
    private String driverId;
    private String driverFirstName;
    private String driverLastName;
    private String driverFullName;
}
