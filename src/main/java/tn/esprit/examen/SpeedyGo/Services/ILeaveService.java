package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Leave;

import java.util.List;

public interface ILeaveService {
    Leave addLeave(Leave leave);
    void deleteLeave(String id);
    Leave getLeave(String id);
    List<Leave> getAllLeaves();
    Leave updateLeave(Leave leave);
    void approveLeave(String id);
    void rejectLeave(String id);
}
