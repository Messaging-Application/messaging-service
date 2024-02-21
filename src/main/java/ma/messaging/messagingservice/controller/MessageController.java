package ma.messaging.messagingservice.controller;


import ma.messaging.messagingservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

}
