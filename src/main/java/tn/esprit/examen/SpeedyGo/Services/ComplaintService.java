package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.ComplaintRepo;
import tn.esprit.examen.SpeedyGo.entities.Complaint;
import tn.esprit.examen.SpeedyGo.entities.ComplaintCategory;
import tn.esprit.examen.SpeedyGo.entities.ComplaintStatus;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ComplaintService implements IComplaint {

    ComplaintRepo complaintRepo;
    @Override
    public Complaint createComplaint(Complaint complaint) {
        return complaintRepo.save(complaint);
    }

    @Override
    public Complaint getComplaint(String id) {
        return complaintRepo.findById(id).orElse(null);
    }

    @Override
    public List<Complaint> ListComplaints() {
        return complaintRepo.findAll();
    }

    @Override
    public void deleteComplaint(String id) {
        complaintRepo.deleteById(id);
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        return complaintRepo.save(complaint);
    }
    @Override
    public void openComplaint(String id) {
        Complaint complaint = complaintRepo.findById(id).orElse(null);
        if (complaint != null) {
            complaint.setStatus(ComplaintStatus.OPENED);
            complaintRepo.save(complaint);
        }
    }

    @Override
    public void treateComplaint(String id) {
        Complaint complaint = complaintRepo.findById(id).orElse(null);
        if (complaint != null) {
            complaint.setStatus(ComplaintStatus.TREATED);
            complaintRepo.save(complaint);
        }
    }
}
