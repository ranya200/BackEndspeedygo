package tn.esprit.examen.SpeedyGo.Services;


import tn.esprit.examen.SpeedyGo.entities.Chat;

import java.util.List;

public interface IChatService {

    public Chat saveMessage(Chat message);
    public List<Chat> getAllMessages();
    public List<Chat> getMessagesBySender(String sender);
    void deleteMessageById(String id);
    List<Chat> getMessagesBetween(String user1, String user2);
}
