package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.examen.SpeedyGo.Services.UserService;
import tn.esprit.examen.SpeedyGo.entities.User;

@CrossOrigin(origins = "http://localhost:4200") // Active CORS pour Angular
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user") // ✅ Vérifie bien ce mapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me") // ✅ Vérifie bien que cet endpoint existe
    public User getCurrentUser() {
        return userService.getOrCreateUser();
    }
}
