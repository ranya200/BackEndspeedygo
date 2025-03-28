package tn.esprit.examen.SpeedyGo.Services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.LeaveRepo;
import tn.esprit.examen.SpeedyGo.Repository.LeaveSettingsRepo;
import tn.esprit.examen.SpeedyGo.entities.Leave;
import tn.esprit.examen.SpeedyGo.entities.LeaveSettings;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j

public class LeaveSettingsService implements ILeaveSettingsService {

    LeaveSettingsRepo leaveRepo;

    @PostConstruct
    public void initDefaultSetting() {
        if (leaveRepo.count() == 0) {
            LeaveSettings defaultSetting = new LeaveSettings();
            defaultSetting.setMaxAllowedDays(10);
            leaveRepo.save(defaultSetting);
        }
    }


    @Override
    public LeaveSettings updateLeaveSettings(int newLimit) {
        LeaveSettings settings = getSettings();
        if (settings != null) {
            settings.setMaxAllowedDays(newLimit);
            return leaveRepo.save(settings);
        }
        return null;
    }

    @Override
    public LeaveSettings getSettings() {
        return leaveRepo.findAll().stream().findFirst().orElse(null);
    }
}
