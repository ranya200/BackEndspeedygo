package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.examen.SpeedyGo.Services.ILeaveStatisticsService;
import tn.esprit.examen.SpeedyGo.entities.LeaveStatistics;

@RequiredArgsConstructor
@RestController
@RequestMapping("/leaveStatistics")
public class LeaveStatisticsController {
    ILeaveStatisticsService leaveStatisticsService;

    @PutMapping("/calculate")
    public LeaveStatistics calculateTotalDaysTaken() {
        return leaveStatisticsService.calculateAndUpdateTotalDaysTaken();
    }

    @GetMapping("/total")
    public LeaveStatistics getTotalDaysTaken() {
        return leaveStatisticsService.getTotalDaysTaken();
    }

}
