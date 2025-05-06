package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.ChatRepo;
import tn.esprit.examen.SpeedyGo.entities.Chat;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChatService implements IChatService {
    @Autowired
    private ChatRepo chatRepository;

    public Chat saveMessage(Chat message) {
        return chatRepository.save(message);
    }

    public List<Chat> getAllMessages() {
        return chatRepository.findAll();
    }

    public List<Chat> getMessagesBySender(String sender) {
        return chatRepository.findBySender(sender); // tu peux ajouter cette méthode dans le repository
    }

    public void deleteMessageById(String id) {
        chatRepository.deleteById(id);
    }

    public List<Chat> getMessagesBetween(String user1, String user2) {
        return chatRepository.findBySenderAndReceiverOrReceiverAndSender(user1, user2, user1, user2);
    }


}
