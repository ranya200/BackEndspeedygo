package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.IComplaint;
import tn.esprit.examen.SpeedyGo.entities.Complaint;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    IComplaint complaintService;


    @GetMapping("/listComlpaints")


    public List<Complaint> ListComplaints() {
        return complaintService.ListComplaints();
    }
    @GetMapping("getComplaint/{id}")
    public Complaint getComplaint(@PathVariable("id") String id) {
        return complaintService.getComplaint(id);
    }

    @PostMapping("/createComplaint")
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return complaintService.createComplaint(complaint);
    }

    @PutMapping("updateComplaint")
    public Complaint updateComplaint(@RequestBody Complaint complaint) {
        return complaintService.updateComplaint(complaint);
    }

    @DeleteMapping("deleteComplaint/{id}")
    public void deleteComplaint(@PathVariable("id") String id) {
        complaintService.deleteComplaint(id);
    }
}
