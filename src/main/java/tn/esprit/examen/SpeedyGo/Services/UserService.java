package tn.esprit.examen.SpeedyGo.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.UserRepository;
import tn.esprit.examen.SpeedyGo.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService  {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            throw new IllegalStateException("Utilisateur non authentifié ou token invalide !");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String userId = jwt.getClaim("sub");
        String username = jwt.getClaim("preferred_username");
        String email = jwt.getClaim("email");
        String firstName = jwt.getClaim("given_name");
        String lastName = jwt.getClaim("family_name");

        Map<String, List<String>> realmAccess = jwt.getClaim("realm_access");
        List<String> roles = (realmAccess != null) ? realmAccess.get("roles") : List.of();

        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        User newUser = new User();
        newUser.setId(userId);
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setRoles(roles);
        newUser.setBadge(null); // optional, can omit


        return userRepository.save(newUser);

    }
    public String getFullNameById(String userId) {
        return userRepository.findById(userId)
                .map(user -> user.getFirstName() + " " + user.getLastName())
                .orElse("Unknown User");
    }


    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllOtherUsers(String currentUserId) {
        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getId().equals(currentUserId))
                .toList();
    }


}
