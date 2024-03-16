package ma.messaging.messagingservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ma.messaging.messagingservice.model.Message;

public class MessageMapper {

    public static Message fromSQSMessage(com.amazonaws.services.sqs.model.Message sqsMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(sqsMessage.getBody(), Message.class);
    }
}
