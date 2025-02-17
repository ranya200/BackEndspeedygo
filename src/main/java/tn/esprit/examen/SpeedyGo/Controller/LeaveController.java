package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.ILeaveService;
import tn.esprit.examen.SpeedyGo.entities.Leave;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/leaves")
public class LeaveController {
    ILeaveService leaveService;

    @Autowired
    public LeaveController(ILeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/all")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @PostMapping("/add")
    public Leave addLeave(@RequestBody Leave leave) {
        return leaveService.addLeave(leave);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLeave(@PathVariable("id") String id) {
        leaveService.deleteLeave(id);
    }

    @PutMapping("/update")
    public Leave updateLeave(@RequestBody Leave leave) {
        return leaveService.updateLeave(leave);
    }

    @GetMapping("/get/{id}")
    public Leave getLeave(@PathVariable("id") String id) {
        return leaveService.getLeave(id);
    }

}
