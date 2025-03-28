package tn.esprit.examen.SpeedyGo.Services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.LeaveRepo;
import tn.esprit.examen.SpeedyGo.Repository.LeaveStatisticsRepo;
import tn.esprit.examen.SpeedyGo.entities.Leave;
import tn.esprit.examen.SpeedyGo.entities.LeaveSettings;
import tn.esprit.examen.SpeedyGo.entities.LeaveStatistics;
import tn.esprit.examen.SpeedyGo.entities.Status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LeaveStatisticsService implements ILeaveStatisticsService{
    LeaveStatisticsRepo leaveStatisticsRepo;
    LeaveRepo leaveRepo;


    @PostConstruct
    private void initDefaultTotalDaysTaken() {
        if (leaveStatisticsRepo.count() == 0) {
            LeaveStatistics statistics = new LeaveStatistics(null, 0);
            leaveStatisticsRepo.save(statistics);
        }
    }


    @Override
    public LeaveStatistics calculateAndUpdateTotalDaysTaken() {
        List<Leave> leaves = leaveRepo.findAll();

        long totalDaysTaken = leaves.stream()
                .filter(l -> l.getEndDate() != null && l.getStartDate() != null)
                .filter(l -> l.getStatus() == Status.APPROVED || l.getStatus() == Status.PENDING)
                .mapToLong(l -> ChronoUnit.DAYS.between(
                                l.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                l.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        ) + 1 // pour inclure la date de fin
                )
                .sum();

        LeaveStatistics statistics = leaveStatisticsRepo.findAll().stream()
                .findFirst()
                .orElse(new LeaveStatistics(null, 0));

        statistics.setTotalDaysTaken((int) totalDaysTaken);
        return leaveStatisticsRepo.save(statistics);
    }



    @Override
    public LeaveStatistics getTotalDaysTaken() {
        return leaveStatisticsRepo.findAll().stream().findFirst().orElse(new LeaveStatistics(null, 0));
    }
}
