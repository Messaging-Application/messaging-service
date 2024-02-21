package ma.messaging.messagingservice.service;

import ma.messaging.messagingservice.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    boolean getMessagesFromUser(UUID senderId, UUID receiverId);
}
