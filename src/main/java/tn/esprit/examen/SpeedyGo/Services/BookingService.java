package tn.esprit.examen.SpeedyGo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.BookingRepository;
import tn.esprit.examen.SpeedyGo.Repository.CarpoolingRepository;
import tn.esprit.examen.SpeedyGo.entities.Booking;
import tn.esprit.examen.SpeedyGo.entities.Carpooling;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private CarpoolingRepository carpoolingRepo;

    public Booking createBooking(Booking booking) {
        Carpooling ride = carpoolingRepo.findById(booking.getRideId()).orElseThrow();
        if (ride.getAvailableSeats() < booking.getSeatsBooked()) {
            throw new IllegalStateException("Not enough seats available");
        }

        booking.setStatus("PENDING");
        return bookingRepo.save(booking);
    }

    public Booking confirmBooking(String bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElseThrow();
        Carpooling ride = carpoolingRepo.findById(booking.getRideId()).orElseThrow();

        if (ride.getAvailableSeats() < booking.getSeatsBooked()) {
            throw new IllegalStateException("Insufficient seats at confirmation");
        }

        booking.setStatus("CONFIRMED");
        ride.setAvailableSeats(ride.getAvailableSeats() - booking.getSeatsBooked());

        carpoolingRepo.save(ride);
        return bookingRepo.save(booking);
    }

    public List<Booking> getBookingsForRide(String rideId) {
        return bookingRepo.findByRideId(rideId);
    }

    public List<Booking> getBookingsByDriver(String driverId) {
        List<Carpooling> rides = carpoolingRepo.findByDriverId(driverId);
        return rides.stream()
                .flatMap(ride -> bookingRepo.findByRideId(ride.getId()).stream())
                .filter(b -> b.getSeatsBooked() > 0)
                .toList();
    }
    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepo.findByPassengerId(userId);
    }


}

