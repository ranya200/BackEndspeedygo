package tn.esprit.examen.SpeedyGo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.CarpoolingRepository;
import tn.esprit.examen.SpeedyGo.entities.Carpooling;

import java.util.List;
import java.util.Optional;

@Service
public class CarpoolingService {

    @Autowired
    private CarpoolingRepository carpoolingRepository;

    public List<Carpooling> getAllRides() {
        return carpoolingRepository.findAll();
    }

    public Optional<Carpooling> getRideById(String id) {
        return carpoolingRepository.findById(id);
    }

    public Carpooling createRide(Carpooling carpooling) {
        return carpoolingRepository.save(carpooling);
    }

    public Carpooling updateRide(String id, Carpooling updatedCarpooling) {
        return carpoolingRepository.findById(id).map(ride -> {
            ride.setDriverName(updatedCarpooling.getDriverName());
            ride.setPickupLocation(updatedCarpooling.getPickupLocation());
            ride.setDropoffLocation(updatedCarpooling.getDropoffLocation());
            ride.setDepartureTime(updatedCarpooling.getDepartureTime());
            ride.setAvailableSeats(updatedCarpooling.getAvailableSeats());
            ride.setPricePerSeat(updatedCarpooling.getPricePerSeat());
            return carpoolingRepository.save(ride);
        }).orElse(null);
    }

    public void deleteRide(String id) {
        carpoolingRepository.deleteById(id);
    }
}