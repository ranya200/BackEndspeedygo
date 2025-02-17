package tn.esprit.examen.SpeedyGo.Controller;

import tn.esprit.examen.SpeedyGo.entities.Chat;
import tn.esprit.examen.SpeedyGo.Services.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    IChatService chatService;
    @Autowired
    public ChatController(IChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/addChat")
    public Chat addChat(@RequestBody Chat chat) {
        return chatService.addChat(chat);
    }

    @DeleteMapping("/deleteChat/{id}")
    public void deleteChat(@PathVariable("id") String id) {
        chatService.deleteChat(id);
    }

    @GetMapping("/getChat/{id}")
    public Chat getChat(@PathVariable("id") String id) {
        return chatService.getChat(id);
    }

    @GetMapping("/getAllChats")
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @PutMapping("/updateChat")
    public void updateChat(@RequestBody Chat chat) {
        chatService.updateChat(chat);
    }

}
