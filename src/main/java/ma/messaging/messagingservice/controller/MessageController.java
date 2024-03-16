package ma.messaging.messagingservice.controller;


import ma.messaging.messagingservice.model.Message;
import ma.messaging.messagingservice.service.MessageService;
import ma.messaging.messagingservice.service.SQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    SQSService sqsService;

    @GetMapping("/{chatId}")
    public List<Message> getMessagesByChatId(@PathVariable UUID chatId) {
        return messageService.getMessagesFromChat(chatId);
    }

    @GetMapping("/save-message")
    public ResponseEntity<String> saveFromSQS(){
        String response = sqsService.receive();

        return ResponseEntity.ok(response);
    }
}
