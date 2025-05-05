package tn.esprit.examen.SpeedyGo.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.MaintenanceRepository;
import tn.esprit.examen.SpeedyGo.Repository.VehicleRepository;
import tn.esprit.examen.SpeedyGo.entities.Maintenance;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleMaintenanceService implements IVehicleMaintenanceService{
    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;

    public Maintenance scheduleMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> getUpcomingMaintenance() {
        return maintenanceRepository.findByCompletedFalse(); // déclencheur basé sur KM, pas date
    }

    public List<Vehicle> getVehiclesThatNeedMaintenanceSoon() {
        double thresholdMarginKm = 500.0;

        List<Maintenance> pending = maintenanceRepository.findByCompletedFalse();

        List<String> vehicleIds = pending.stream()
                .filter(m -> {
                    double kmRemaining = m.getTriggerDistanceKm() - m.getCurrentDistanceKm();
                    return kmRemaining >= 0 && kmRemaining <= thresholdMarginKm;
                })
                .map(Maintenance::getVehicleId)
                .distinct()
                .collect(Collectors.toList());

        return vehicleRepository.findAll().stream()
                .filter(v -> vehicleIds.contains(v.getIdV()))
                .collect(Collectors.toList());
    }


}
