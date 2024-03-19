package ma.messaging.messagingservice.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import ma.messaging.messagingservice.repository.MessageRepository;
import ma.messaging.messagingservice.utils.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SQSService {

    private final String SQS_QUEUE_URL = "https://sqs.eu-central-1.amazonaws.com/569076552881/message-queue";

    @Autowired
    MessageRepository messageRepository;

    private AmazonSQS sqsClient;

    @PostConstruct
    public void init() {
        this.sqsClient = AmazonSQSClientBuilder.defaultClient();
    }

    public String receive(){
        log.info("Saving Message");
        List<Message> messages = sqsClient.receiveMessage(SQS_QUEUE_URL).getMessages();
        List<ma.messaging.messagingservice.model.Message> messagesToSave = new ArrayList<>();
        try{
            for(Message m : messages ){
                messagesToSave.add(MessageMapper.fromSQSMessage(m));
                log.info("Processed message: {}", m.getBody());

                sqsClient.deleteMessage(new DeleteMessageRequest()
                        .withQueueUrl(SQS_QUEUE_URL)
                        .withReceiptHandle(m.getReceiptHandle()));
                log.info("Deleted message from SQS: {}", m.getReceiptHandle());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        if(messages.isEmpty())
            return "Didn't have any message to read";

        messageRepository.saveAll(messagesToSave);
        log.info("Saved Message with success");



        return "Message has been processed";
    }

}
