package tn.esprit.examen.SpeedyGo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.SpeedyGo.entities.Chat;

import java.util.List;

@Repository

public interface ChatRepo extends MongoRepository<Chat, String> {
    List<Chat> findBySender(String sender);
    void deleteById(String id);
    List<Chat> findBySenderAndReceiver(String sender, String receiver);
    List<Chat> findBySenderAndReceiverOrReceiverAndSender(String sender, String receiver, String receiver2, String sender2);


}
