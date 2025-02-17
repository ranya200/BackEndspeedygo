package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.LeaveRepo;
import tn.esprit.examen.SpeedyGo.entities.Leave;

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


}
