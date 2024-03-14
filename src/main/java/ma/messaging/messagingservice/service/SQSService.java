package ma.messaging.messagingservice.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
//import ma.messaging.messagingservice.model.Message;
import ma.messaging.messagingservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class SQSService {

    @Autowired
    MessageRepository messageRepository;

    private AmazonSQS sqsClient;

    //private final String queueUrl = "https://sqs.eu-central-1.amazonaws.com/569076552881/message-queue";

    @SqsListener("message-queue")
    public void receive(Message message){
        log.info("Message Received");
        //messageRepository.save(message);
    }

}
