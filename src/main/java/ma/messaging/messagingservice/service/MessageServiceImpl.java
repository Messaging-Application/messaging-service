package ma.messaging.messagingservice.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService{
    @Override
    public boolean getMessagesFromUser(UUID fromUserId, UUID toUserId) {
        return false;
    }
}
