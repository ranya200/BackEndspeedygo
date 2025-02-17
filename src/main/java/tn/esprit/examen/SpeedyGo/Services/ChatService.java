package tn.esprit.examen.SpeedyGo.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.examen.SpeedyGo.Repository.ChatRepo;
import tn.esprit.examen.SpeedyGo.entities.Chat;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChatService implements IChatService {
    ChatRepo chatRepo;

    @Override
    public Chat addChat(Chat chat) {
        return chatRepo.save(chat);
    }

    @Override
    public void deleteChat(String id) {
        chatRepo.deleteById(id);
    }

    @Override
    public Chat getChat(String id) {
        return chatRepo.findById(id).get();
    }

    @Override
    public void updateChat(Chat chat) {
        chatRepo.save(chat);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepo.findAll();
    }


}
