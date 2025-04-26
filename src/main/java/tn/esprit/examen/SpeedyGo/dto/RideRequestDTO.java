package tn.esprit.examen.SpeedyGo.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {
    private String id;
    private String passengerId;
    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime preferredTime;
    private int seatsNeeded;
    private String status;
    private String confirmedDriverId;

    private List<DriverDTO> appliedDrivers;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DriverDTO {
        private String id;
        private String fullName;
    }
}
