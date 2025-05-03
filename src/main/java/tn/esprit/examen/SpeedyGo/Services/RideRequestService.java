package tn.esprit.examen.SpeedyGo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.RideRequestRepository;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.dto.RideRequestDTO;
import tn.esprit.examen.SpeedyGo.entities.RideRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public RideRequest createRequest(RideRequest request) {
        request.setStatus("PENDING");
        return repository.save(request);
    }

    public List<RideRequest> getAllRequests() {
        return repository.findAll();
    }

    public RideRequest updateRequestStatus(String id, String status) {
        RideRequest request = repository.findById(id).orElseThrow();
        request.setStatus(status);
        return repository.save(request);
    }

    public void applyToRequest(String requestId, String driverId) {
        RideRequest request = repository.findById(requestId).orElseThrow(() ->
                new RuntimeException("Ride request not found"));

        if (!request.getAppliedDriverIds().contains(driverId)) {
            request.getAppliedDriverIds().add(driverId);
            repository.save(request);
        }
    }
    public void confirmDriver(String requestId, String driverId) {
        RideRequest request = repository.findById(requestId).orElseThrow(() ->
                new RuntimeException("Ride request not found"));

        if (!request.getAppliedDriverIds().contains(driverId)) {
            throw new IllegalStateException("This driver has not applied for the request.");
        }

        request.setConfirmedDriverId(driverId);
        request.setStatus("MATCHED");
        repository.save(request);
    }

    public Optional<RideRequest> getById(String id) {
        return repository.findById(id);
    }



//    public List<RideRequestDTO> getAllRequestsWithDriverNames() {
//        List<RideRequest> requests = repository.findAll();
//        List<RideRequestDTO> result = new ArrayList<>();
//
//        for (RideRequest req : requests) {
//            List<String> names = req.getAppliedDriverIds().stream()
//                    .map(id -> userRepository.findById(id)
//                            .map(u -> u.getFirstName() + " " + u.getLastName())
//                            .orElse("Unknown"))
//                    .toList();
//
//            RideRequestDTO dto = new RideRequestDTO(
//                    req.getId(),
//                    req.getPassengerId(),
//                    req.getPickupLocation(),
//                    req.getDropoffLocation(),
//                    req.getPreferredTime(),
//                    req.getSeatsNeeded(),
//                    req.getStatus(),
//                    names,
//                    req.getConfirmedDriverId()
//            );
//            result.add(dto);
//        }
//        return result;
//    }



    public List<RideRequestDTO> getAllWithDriverNames() {
        List<RideRequest> requests = repository.findAll();
        List<RideRequestDTO> result = new ArrayList<>();

        for (RideRequest request : requests) {
            List<RideRequestDTO.DriverDTO> drivers = new ArrayList<>();

            for (String driverId : request.getAppliedDriverIds()) {
                userRepository.findById(driverId).ifPresent(user -> {
                    String fullName = user.getFirstName() + " " + user.getLastName();
                    drivers.add(new RideRequestDTO.DriverDTO(user.getId(), fullName));
                });
            }

            RideRequestDTO dto = new RideRequestDTO(
                    request.getId(),
                    request.getPassengerId(),
                    request.getPickupLocation(),
                    request.getDropoffLocation(),
                    request.getPreferredTime(),
                    request.getSeatsNeeded(),
                    request.getStatus(),
                    request.getConfirmedDriverId(),
                    drivers
            );

            result.add(dto);
        }

        return result;
    }

    public void unapplyToRideRequest(String requestId, String driverId) {
        RideRequest request = repository.findById(requestId).orElseThrow(() ->
                new RuntimeException("Ride request not found"));

        request.getAppliedDriverIds().remove(driverId); // Remove driver from the list
        repository.save(request); // Save updated request
    }
    public void declineDriverFromRequest(String requestId, String driverId) {
        RideRequest request = repository.findById(requestId).orElseThrow(() ->
                new RuntimeException("Ride request not found"));

        // Remove the driver from the list of applicants
        request.getAppliedDriverIds().remove(driverId);
        repository.save(request);
    }


    public RideRequest declineConfirmedRide(String requestId) {
        RideRequest request = repository.findById(requestId).orElseThrow(() -> new RuntimeException("Ride request not found"));

        // Decline the ride by resetting confirmedDriverId and changing status
        request.setConfirmedDriverId(null);
        request.setStatus("PENDING"); // Or whatever status is appropriate for your flow
        return repository.save(request);
    }


}

