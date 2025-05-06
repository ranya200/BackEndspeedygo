package tn.esprit.examen.SpeedyGo.Services;

import tn.esprit.examen.SpeedyGo.entities.User;

import java.util.List;

public interface IUserService {
    List<User> getAllOtherUsers(String currentUserId);
}
