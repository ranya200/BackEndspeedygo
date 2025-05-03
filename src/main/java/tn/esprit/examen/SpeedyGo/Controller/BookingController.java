package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Repository.CarpoolingRepository;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.Services.BookingService;
import tn.esprit.examen.SpeedyGo.dto.BookingDTO;
import tn.esprit.examen.SpeedyGo.entities.Booking;
import tn.esprit.examen.SpeedyGo.entities.Carpooling;
import tn.esprit.examen.SpeedyGo.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final CarpoolingRepository carpoolingRepo;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable String id) {
        try {
            Booking confirmedBooking = bookingService.confirmBooking(id);
            return ResponseEntity.ok(confirmedBooking);
        } catch (IllegalStateException e) {
            // If there are no available seats, respond with a friendly error message
            return ResponseEntity.status(400).body(null);  // Return 400 for "Bad Request"
        }
    }


    @GetMapping("/ride/{rideId}")
    public List<BookingDTO> getBookingsForRide(@PathVariable String rideId) {
        List<Booking> bookings = bookingService.getBookingsForRide(rideId);
        return bookings.stream()
                .map(booking -> mapToDTO(booking))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-driver/{driverId}")
    public List<BookingDTO> getBookingsByDriver(@PathVariable String driverId) {
        List<Booking> bookings = bookingService.getBookingsByDriver(driverId);
        return bookings.stream()
                .map(booking -> mapToDTO(booking))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<BookingDTO> getBookingsByUser(@PathVariable String userId) {
        List<Booking> bookings = bookingService.getBookingsByUser(userId);
        return bookings.stream()
                .map(booking -> mapToDTO(booking))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private BookingDTO mapToDTO(Booking booking) {
        Carpooling ride = carpoolingRepo.findById(booking.getRideId()).orElse(null);
        User passenger = userRepository.findById(booking.getPassengerId()).orElse(null);
        if (ride != null) {
            String fullName = (passenger != null)
                    ? passenger.getFirstName() + " " + passenger.getLastName()
                    : "Unknown Passenger";

            return BookingDTO.from(booking,
                    ride.getPickupLocation(),
                    ride.getDropoffLocation(),
                    ride.getDepartureTime(),
                    fullName);
        }
        return null;
    }
    @PutMapping("/{id}/decline")
    public ResponseEntity<Booking> declineBooking(@PathVariable String id) {
        Booking declined = bookingService.declineBooking(id);
        return ResponseEntity.ok(declined);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable String id) {
        Booking canceled = bookingService.cancelBooking(id);
        return ResponseEntity.ok(canceled);
    }


}
