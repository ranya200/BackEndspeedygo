package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.LeaveSettingsRepo;
import tn.esprit.examen.SpeedyGo.entities.LeaveSettings;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class LeaveSettingsService implements ILeaveSettingsService {

    LeaveSettingsRepo leaveRepo;

    @Override
    public LeaveSettings getSettings() {
        return leaveRepo.findAll().stream().findFirst().orElseGet(() -> {
            LeaveSettings defaultSettings = new LeaveSettings();
            defaultSettings.setMaxAllowedDays(10); // Default to 10 days
            return leaveRepo.save(defaultSettings);
        });
    }

    @Override
    public LeaveSettings updateMaxAllowedDays(int days) {
        LeaveSettings settings = getSettings();
        settings.setMaxAllowedDays(days);
        return leaveRepo.save(settings);
    }
}
