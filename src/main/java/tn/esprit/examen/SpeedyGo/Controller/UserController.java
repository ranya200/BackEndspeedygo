package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Services.UserService;
import tn.esprit.examen.SpeedyGo.entities.User;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public User getCurrentUser() {
        return userService.getOrCreateUser();
    }
<<<<<<< HEAD
=======
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
>>>>>>> origin/main
}
