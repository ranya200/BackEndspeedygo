package tn.esprit.examen.SpeedyGo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.SpeedyGo.Repository.FavoriteRepository;
import tn.esprit.examen.SpeedyGo.entities.Favorite;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor

public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestBody Favorite favorite) {
        if (favoriteRepository.findByUserIdAndRideId(favorite.getUserId(), favorite.getRideId()).isPresent()) {
            return ResponseEntity.status(409).body("Already in favorites");
        }
        return ResponseEntity.ok(favoriteRepository.save(favorite));
    }

    @DeleteMapping
    public ResponseEntity<?> removeFavorite(@RequestParam String userId, @RequestParam String rideId) {
        favoriteRepository.deleteByUserIdAndRideId(userId, rideId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public List<Favorite> getUserFavorites(@PathVariable String userId) {
        return favoriteRepository.findByUserId(userId);
    }
}
