package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    @Query("{ 'brand': { $regex: ?0, $options: 'i' } }")
    List<Vehicle> findByBrand(String brand);
    // Trouver les véhicules déjà assignés à un livreur
    List<Vehicle> findByAssignedToDriverId(String driverId);

}
