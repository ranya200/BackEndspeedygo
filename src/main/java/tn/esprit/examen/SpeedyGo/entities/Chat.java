package tn.esprit.examen.SpeedyGo.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chats") // MongoDB collection
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chat {

    @Id
    private String id;
    private String sender;
    private String receiver;
    private String content;
    private MessageType type;
    private LocalDateTime timestamp;

}
