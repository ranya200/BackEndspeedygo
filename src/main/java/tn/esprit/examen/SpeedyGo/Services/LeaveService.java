package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.LeaveRepo;
import tn.esprit.examen.SpeedyGo.Repository.LeaveSettingsRepo;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.entities.*;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class LeaveService implements ILeaveService {
    LeaveRepo leaveRepo;
    UserRepository userRepository;
    LeaveSettingsService leaveSettingsService;
    LeaveSettingsRepo leaveSettingsRepo;

    @Override
    public Leave createLeave(Leave leave) {
        leave.setStatus(Status.PENDING);
        return leaveRepo.save(leave);
    }

    @Override
    public Map<String, Object> checkLeaveExceeding(Leave leave) {
        long daysRequested = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1;
        int maxAllowed = leaveSettingsService.getSettings().getMaxAllowedDays();

        // 🔁 On récupère toutes les demandes existantes du conducteur
        List<Leave> existingLeaves = leaveRepo.findByDriverId(leave.getDriverId()).stream()
                .filter(l -> l.getStatus() == Status.APPROVED || l.getStatus() == Status.PENDING)
                .collect(Collectors.toList());

        // ✅ Ignore la demande actuelle si c’est un update (si elle a un ID)
        if (leave.getId() != null) {
            existingLeaves = existingLeaves.stream()
                    .filter(l -> !l.getId().equals(leave.getId()))
                    .collect(Collectors.toList());
        }

        // 🧮 On calcule le total des jours *hors* la demande actuelle
        long totalTaken = existingLeaves.stream()
                .mapToLong(l -> ChronoUnit.DAYS.between(l.getStartDate(), l.getEndDate()) + 1)
                .sum();

        long totalAfter = totalTaken + daysRequested;
        boolean exceeds = totalAfter > maxAllowed;

        Map<String, Object> result = new HashMap<>();
        result.put("daysRequested", daysRequested);
        result.put("totalBefore", totalTaken);
        result.put("totalAfter", totalAfter);
        result.put("maxAllowed", maxAllowed);
        result.put("exceeds", exceeds);
        result.put("exceededDays", Math.max(0, totalAfter - maxAllowed));

        // 🔎 Log optionnel (utile pour debug)
        System.out.println("🔢 checkLeaveExceeding: daysRequested = " + daysRequested);
        System.out.println("📄 Existing leaves count = " + existingLeaves.size());
        System.out.println("📊 totalTaken (excluding current) = " + totalTaken);
        System.out.println("🧮 totalAfter = " + totalAfter + " / exceeds = " + exceeds);

        return result;
    }


    @Override
    public List<Leave> getLeavesByDriver(String driverId) {
        return leaveRepo.findByDriverId(driverId);
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepo.findAll();
    }

    @Override
    public Leave updateLeave(String id, Leave updatedLeave, boolean allowExceeding) {
        Leave existingLeave = leaveRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        if (!existingLeave.getStatus().equals(Status.PENDING)) {
            throw new IllegalStateException("Only pending leave requests can be updated.");
        }

        long updatedDays = ChronoUnit.DAYS.between(updatedLeave.getStartDate(), updatedLeave.getEndDate()) + 1;
        System.out.println("🔢 UpdatedDays (nouvelle demande) = " + updatedDays);
        System.out.println("🗓️ StartDate: " + updatedLeave.getStartDate() + " / EndDate: " + updatedLeave.getEndDate());

        List<Leave> otherLeaves = leaveRepo.findByDriverId(existingLeave.getDriverId()).stream()
                .filter(l -> !l.getId().equals(existingLeave.getId()))
                .filter(l -> l.getStatus() == Status.APPROVED || l.getStatus() == Status.PENDING)
                .toList();

        long totalDaysBefore = otherLeaves.stream()
                .mapToLong(l -> ChronoUnit.DAYS.between(l.getStartDate(), l.getEndDate()) + 1)
                .sum();

        System.out.println("📊 TotalDaysBefore (autres demandes) = " + totalDaysBefore);
        System.out.println("📄 Nombre de demandes exclues = " + otherLeaves.size());

        LeaveSettings settings = leaveSettingsRepo.findTopByOrderByIdDesc()
                .orElse(new LeaveSettings(null, 10));

        System.out.println("⚙️ MaxAllowed = " + settings.getMaxAllowedDays());

        long totalAfterUpdate = totalDaysBefore + updatedDays;
        System.out.println("🧮 Total après mise à jour = " + totalAfterUpdate);

        if (!allowExceeding && totalAfterUpdate > settings.getMaxAllowedDays()) {
            System.out.println("❌ Dépassement détecté : " + (totalAfterUpdate - settings.getMaxAllowedDays()) + " jours");
            throw new IllegalArgumentException("Leave days limit exceeded for this driver.");
        }

        updatedLeave.setId(existingLeave.getId());
        updatedLeave.setStatus(existingLeave.getStatus());
        updatedLeave.setDriverId(existingLeave.getDriverId());

        System.out.println("✅ Mise à jour de la demande validée.");
        return leaveRepo.save(updatedLeave);
    }


    @Override
    public void deleteLeave(String id) {
        Leave leave = leaveRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        if (leave.getStatus() != Status.PENDING) {
            throw new IllegalStateException("Only pending leaves can be deleted.");
        }

        leaveRepo.deleteById(id);
    }

    @Override
    public Optional<Leave> getLeaveById(String id) {
        return leaveRepo.findById(id);
    }



    @Override
    public Leave updateLeaveStatus(String leaveId, Status  status) {
        Leave leave = leaveRepo.findById(leaveId).orElseThrow();
        leave.setStatus(status);
        return leaveRepo.save(leave);
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

    @Override
    public long calculateTotalLeaveDays(String driverId) {
        List<Leave> leaves = leaveRepo.findByDriverId(driverId)
                .stream()
                .filter(leave -> leave.getStatus() == Status.APPROVED || leave.getStatus() == Status.PENDING)
                .toList();

        return leaves.stream()
                .mapToLong(leave ->
                        ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1 // +1 pour inclure le dernier jour
                ).sum();
    }

    @Override
    public List<Map<String, Object>> getTotalDaysByDriver() {
        List<User> allDrivers = userRepository.findAll()
                .stream()
                .filter(u -> u.getRoles().contains("DRIVER"))
                .toList();

        List<Leave> allLeaves = leaveRepo.findAll();

        List<Map<String, Object>> result = new ArrayList<>();

        for (User driver : allDrivers) {
            long totalDays = allLeaves.stream()
                    .filter(leave -> leave.getDriverId() != null && leave.getDriverId().equals(driver.getId()))
                    .filter(leave -> leave.getStatus() == Status.APPROVED || leave.getStatus() == Status.PENDING)
                    .mapToLong(leave -> ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1)
                    .sum();

            Map<String, Object> entry = new HashMap<>();
            entry.put("firstName", driver.getFirstName());
            entry.put("lastName", driver.getLastName());
            entry.put("totalDaysTaken", totalDays);
            result.add(entry);
        }

        return result;
    }

    public List<Map<String, Object>> getDetailedSummaryByDriver() {
        List<User> drivers = userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.equalsIgnoreCase("driver")))
                .toList();

        List<Leave> allLeaves = leaveRepo.findAll();

        List<Map<String, Object>> summary = new ArrayList<>();

        for (User driver : drivers) {
            long totalDays = allLeaves.stream()
                    .filter(leave -> leave.getDriverId() != null && leave.getDriverId().equals(driver.getId()))
                    .filter(leave -> leave.getStatus() == Status.APPROVED || leave.getStatus() == Status.PENDING)
                    .mapToLong(leave -> ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate()) + 1)
                    .sum();

            Map<String, Object> map = new HashMap<>();
            map.put("driverId", driver.getId());
            map.put("firstName", driver.getFirstName());
            map.put("lastName", driver.getLastName());
            map.put("totalDaysTaken", totalDays);
            map.put("limit", leaveSettingsService.getSettings().getMaxAllowedDays());
            summary.add(map);
        }

        return summary;
    }

    public List<LeaveDTO> getAllLeavesWithDriverNames() {
        List<Leave> leaves = leaveRepo.findAll();
        List<User> users = userRepository.findAll();
        Map<String, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, user -> user));

        return leaves.stream().map(leave -> {
            User driver = userMap.get(leave.getDriverId());

            String firstName = (driver != null) ? driver.getFirstName() : "Unknown";
            String lastName = (driver != null) ? driver.getLastName() : "";
            String fullName = firstName + " " + lastName;

            LeaveDTO dto = new LeaveDTO();
            dto.setId(leave.getId());
            dto.setStartDate(leave.getStartDate());
            dto.setEndDate(leave.getEndDate());
            dto.setReason(leave.getReason());
            dto.setStatus(leave.getStatus());
            dto.setDriverId(leave.getDriverId());
            dto.setDriverFirstName(firstName);
            dto.setDriverLastName(lastName);
            dto.setDriverFullName(fullName.trim());

            return dto;
        }).toList();
    }

    public LeaveDTO getLeaveWithDriverNameById(String id) {
        Leave leave = leaveRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        User driver = userRepository.findById(leave.getDriverId())
                .orElse(new User("", "", "", "Unknown", "Driver", List.of()));

        LeaveDTO dto = new LeaveDTO();
        dto.setId(leave.getId());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus());
        dto.setDriverId(leave.getDriverId());
        dto.setDriverFirstName(driver.getFirstName());
        dto.setDriverLastName(driver.getLastName());
        dto.setDriverFullName((driver.getFirstName() + " " + driver.getLastName()).trim());

        return dto;
    }


}
