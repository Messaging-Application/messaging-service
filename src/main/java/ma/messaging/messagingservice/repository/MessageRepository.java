package ma.messaging.messagingservice.repository;

import com.mongodb.MongoSocketReadTimeoutException;
import ma.messaging.messagingservice.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    @Retryable(value = MongoSocketReadTimeoutException.class, maxAttempts = 4, backoff = @Backoff(delay = 1000))
    List<Message> findByChatId(UUID chat_id);

}
