package ma.messaging.messagingservice.service;

import ma.messaging.messagingservice.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    List<Message> getMessagesFromChat(UUID chatId);

    Message storeMessage(Message message);

}
