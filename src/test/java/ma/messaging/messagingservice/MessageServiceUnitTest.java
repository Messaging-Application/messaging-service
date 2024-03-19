package ma.messaging.messagingservice;

import ma.messaging.messagingservice.model.Message;
import ma.messaging.messagingservice.repository.MessageRepository;
import ma.messaging.messagingservice.service.MessageService;
import ma.messaging.messagingservice.service.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessageServiceUnitTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMessagesByChatId() {
        UUID chatId = UUID.randomUUID();
        List<Message> expectedMessages = Arrays.asList(
                new Message(UUID.randomUUID(), chatId, 1, 2, "Message 1"),
                new Message(UUID.randomUUID(), chatId, 3, 4, "Message 2")
        );

        when(messageRepository.findByChatId(chatId)).thenReturn(expectedMessages);

        List<Message> actualMessages = messageService.getMessagesFromChat(chatId);

        assertEquals(expectedMessages.size(), actualMessages.size());
        assertEquals(expectedMessages, actualMessages);
    }

    @Test
    public void testStoreMessage() {
        Message messageToStore = new Message(UUID.randomUUID(), UUID.randomUUID(), 5, 6, "New Message");

        when(messageRepository.save(messageToStore)).thenReturn(messageToStore);

        Message storedMessage = messageService.storeMessage(messageToStore);

        assertEquals(messageToStore.getMessageContent(), storedMessage.getMessageContent());
        assertEquals(messageToStore.getSenderId(), storedMessage.getSenderId());
    }
}
