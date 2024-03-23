package ma.messaging.messagingservice.controller;


import lombok.extern.slf4j.Slf4j;
import ma.messaging.messagingservice.model.Message;
import ma.messaging.messagingservice.service.MessageService;
import ma.messaging.messagingservice.service.SQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600, allowCredentials = "true")
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
        log.info("SAVING MESSAGE CONTROLLER CALLED!");
        String response = sqsService.receive();


        return ResponseEntity.ok(response);
    }
}
