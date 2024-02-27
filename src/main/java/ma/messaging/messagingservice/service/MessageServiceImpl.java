package ma.messaging.messagingservice.service;

import ma.messaging.messagingservice.model.Message;
import ma.messaging.messagingservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> getMessagesFromChat(UUID chatId) {
        return messageRepository.findByChatId(chatId);
    }

    @Override
    public Message storeMessage(Message message) {

        return messageRepository.save(message);
    }


}
