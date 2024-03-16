package ma.messaging.messagingservice.repository;

import ma.messaging.messagingservice.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByChatId(UUID chat_id);

}
