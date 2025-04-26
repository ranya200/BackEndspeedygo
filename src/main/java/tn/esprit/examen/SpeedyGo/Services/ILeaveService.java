package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Leave;
import tn.esprit.examen.SpeedyGo.entities.LeaveDTO;
import tn.esprit.examen.SpeedyGo.entities.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ILeaveService {
    Leave createLeave(Leave leave);
    List<Leave> getLeavesByDriver(String driverId);
    List<Leave> getAllLeaves(); // Pour l’admin
    Leave updateLeaveStatus(String leaveId, Status status);
    void approveLeave(String id);
    void rejectLeave(String id);
    Optional<Leave> getLeaveById(String id);
    void deleteLeave(String id);
    long calculateTotalLeaveDays(String driverId);
    List<Map<String, Object>> getTotalDaysByDriver();
    List<Map<String, Object>> getDetailedSummaryByDriver();
    List<LeaveDTO> getAllLeavesWithDriverNames();
    LeaveDTO getLeaveWithDriverNameById(String id);
    Map<String, Object> checkLeaveExceeding(Leave leave);
    Leave updateLeave(String id, Leave updatedLeave, boolean allowExceeding);
    default Leave updateLeave(String id, Leave updatedLeave) {
        return updateLeave(id, updatedLeave, false);
    }
}
