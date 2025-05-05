package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Maintenance;
import tn.esprit.examen.SpeedyGo.entities.Vehicle;

import java.util.List;

public interface IVehicleMaintenanceService {
    Maintenance scheduleMaintenance(Maintenance maintenance);
    List<Maintenance> getUpcomingMaintenance();
    List<Vehicle> getVehiclesThatNeedMaintenanceSoon();
}
