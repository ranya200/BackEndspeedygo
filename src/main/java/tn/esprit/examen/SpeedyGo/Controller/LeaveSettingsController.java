package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.ILeaveSettingsService;
import tn.esprit.examen.SpeedyGo.entities.LeaveSettings;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/leaveSettings")
public class LeaveSettingsController {
    ILeaveSettingsService leaveService;

    @Autowired
    public LeaveSettingsController(ILeaveSettingsService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    public LeaveSettings getSettings() {
        return leaveService.getSettings();
    }

    @PutMapping("/update")
    public LeaveSettings updateMaxAllowedDays(@RequestParam int maxDays) {
        return leaveService.updateLeaveSettings(maxDays);
    }
}
