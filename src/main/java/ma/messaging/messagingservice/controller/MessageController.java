package ma.messaging.messagingservice.controller;


import ma.messaging.messagingservice.model.Message;
import ma.messaging.messagingservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/{chatId}")
    public List<Message> getMessagesByChatId(@PathVariable UUID chatId) {
        return messageService.getMessagesFromChat(chatId);
    }
}
