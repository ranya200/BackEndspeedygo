package tn.esprit.examen.SpeedyGo.dto;

import lombok.*;
import tn.esprit.examen.SpeedyGo.entities.Booking;
import tn.esprit.examen.SpeedyGo.entities.Carpooling;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    private String id;
    private String rideId;
    private String passengerId;
    private int seatsBooked;
    private String status;
    private String passengerName;


    private String pickupLocation;
    private String dropoffLocation;
    private LocalDateTime departureTime;


    public static BookingDTO from(Booking booking, String pickup, String dropoff, LocalDateTime time, String passengerName) {
        return BookingDTO.builder()
                .id(booking.getId())
                .rideId(booking.getRideId())
                .passengerId(booking.getPassengerId())
                .seatsBooked(booking.getSeatsBooked())
                .status(booking.getStatus())
                .pickupLocation(pickup)
                .dropoffLocation(dropoff)
                .departureTime(time)
                .passengerName(passengerName)
                .build();
    }


}
