package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.Complaint;

import java.util.List;

public interface IComplaint {

    Complaint createComplaint(Complaint complaint);

    Complaint getComplaint(String id);

    List<Complaint> ListComplaints();

    void deleteComplaint(String id);
    Complaint updateComplaint(Complaint complaint);
}
