package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.LeaveRepo;
import tn.esprit.examen.SpeedyGo.entities.Leave;
import tn.esprit.examen.SpeedyGo.entities.Status;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LeaveService implements ILeaveService {
    LeaveRepo leaveRepo;

    @Override
    public Leave addLeave(Leave leave) {
        return leaveRepo.save(leave);
    }

    @Override
    public void deleteLeave(String id) {
        leaveRepo.deleteById(id);
    }

    @Override
    public Leave updateLeave(Leave leave) {
        return leaveRepo.save(leave);
    }

    @Override
    public Leave getLeave(String id) {
        return leaveRepo.findById(id).orElse(null);
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepo.findAll();
    }

    @Override
    public void approveLeave(String id) {
        Leave leave = leaveRepo.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus(Status.APPROVED);
            leaveRepo.save(leave);
        }
    }

    @Override
    public void rejectLeave(String id) {
        Leave leave = leaveRepo.findById(id).orElse(null);
        if (leave != null) {
            leave.setStatus(Status.REJECTED);
            leaveRepo.save(leave);
        }
    }



}
