package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Chat;

import java.util.List;

public interface IChatService {
    Chat addChat(Chat chat);
    void deleteChat(String id);
    Chat getChat(String id);
    List<Chat> getAllChats();
    void updateChat(Chat chat);
}
