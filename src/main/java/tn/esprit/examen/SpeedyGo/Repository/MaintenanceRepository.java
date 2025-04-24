package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.examen.SpeedyGo.entities.Maintenance;

import java.util.List;

public interface MaintenanceRepository extends MongoRepository<Maintenance, String> {
    // ⚠️ Toutes les maintenances pas encore faites
    List<Maintenance> findByCompletedFalse();

    // ⚠️ Celles à faire (trigger atteint + pas encore complétées)
    List<Maintenance> findByTriggerDistanceKmLessThanEqualAndCompletedIsFalse(double currentKm);

    // 🔎 Pour un véhicule donné
    List<Maintenance> findByVehicleId(String vehicleId);
}
