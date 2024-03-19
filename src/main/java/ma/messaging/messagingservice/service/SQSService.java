package ma.messaging.messagingservice.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
//import ma.messaging.messagingservice.model.Message;
import ma.messaging.messagingservice.repository.MessageRepository;
import ma.messaging.messagingservice.utils.MessageMapper;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Slf4j
@Service
public class SQSService {

    private final String SQS_QUEUE_URL = "https://sqs.eu-central-1.amazonaws.com/569076552881/message-queue";

    @Autowired
    MessageRepository messageRepository;

    private AmazonSQS sqsClient;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @PostConstruct
    public void init() {
        this.sqsClient = AmazonSQSClientBuilder.standard()
                .withRegion(awsRegion)
                .build();
    }

    public String receive(){
        log.info("Saving Message");
        List<Message> messages = sqsClient.receiveMessage(SQS_QUEUE_URL).getMessages();
        if(messages.isEmpty())
            return "Didn't have any message to read";



        try {
            messageRepository.save(MessageMapper.fromSQSMessage(messages.get(0)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return "Message has been processed";
    }

}
