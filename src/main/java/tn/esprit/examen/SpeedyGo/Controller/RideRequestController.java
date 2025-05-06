package tn.esprit.examen.SpeedyGo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.RideRequestService;
import tn.esprit.examen.SpeedyGo.dto.RideRequestDTO;
import tn.esprit.examen.SpeedyGo.entities.RideRequest;
import tn.esprit.examen.SpeedyGo.dto.MessageResponse;

import java.util.List;

@RestController
@RequestMapping("/api/ride-requests")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;

    @PostMapping
    public ResponseEntity<RideRequest> createRequest(@RequestBody RideRequest request) {
        RideRequest created = rideRequestService.createRequest(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<RideRequest> getAllRequests() {
        return rideRequestService.getAllRequests();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<RideRequest> updateStatus(@PathVariable String id, @RequestParam String status) {
        return ResponseEntity.ok(rideRequestService.updateRequestStatus(id, status));
    }

    @PostMapping("/{requestId}/apply/{driverId}")
    public ResponseEntity<MessageResponse> applyToRequest(
            @PathVariable String requestId,
            @PathVariable String driverId) {

        rideRequestService.applyToRequest(requestId, driverId);

        return ResponseEntity.ok(new MessageResponse("Driver applied successfully"));
    }

    @PutMapping("/{requestId}/confirm/{driverId}")
    public ResponseEntity<MessageResponse> confirmDriver(
            @PathVariable String requestId,
            @PathVariable String driverId) {

        rideRequestService.confirmDriver(requestId, driverId);

        return ResponseEntity.ok(new MessageResponse("Driver confirmed successfully"));
    }
    @GetMapping("/with-names")
    public List<RideRequestDTO> getRequestsWithDriverNames() {
        return rideRequestService.getAllWithDriverNames();
    }

    @PutMapping("/{requestId}/unapply/{driverId}")
    public ResponseEntity<RideRequest> unapplyFromRideRequest(
            @PathVariable String requestId, @PathVariable String driverId) {
        rideRequestService.unapplyToRideRequest(requestId, driverId);
        return ResponseEntity.ok().build(); // Respond with a success status
    }
    @PutMapping("/{requestId}/decline/{driverId}")
    public ResponseEntity<Void> declineDriver(@PathVariable String requestId, @PathVariable String driverId) {
        rideRequestService.declineDriverFromRequest(requestId, driverId);
        return ResponseEntity.ok().build(); // Respond with OK if successful
    }

    @PutMapping("/{id}/decline")
    public ResponseEntity<RideRequest> declineConfirmedRide(@PathVariable String id) {
        RideRequest updatedRequest = rideRequestService.declineConfirmedRide(id);
        return ResponseEntity.ok(updatedRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRideRequest(@PathVariable String id) {
        rideRequestService.deleteRideRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RideRequest> editRideRequest(@PathVariable String id, @RequestBody RideRequest updatedRequest) {
        try {
            RideRequest updated = rideRequestService.updateRideRequest(id, updatedRequest);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

