package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Leave;
import tn.esprit.examen.SpeedyGo.entities.LeaveSettings;

import java.util.List;

public interface ILeaveSettingsService {
    LeaveSettings getSettings();
    LeaveSettings updateLeaveSettings(int newLimit);
}
