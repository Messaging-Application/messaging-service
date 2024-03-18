package ma.messaging.messagingservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class Message {

    @Getter
    @Id
    @JsonProperty("message_id")
    private UUID messageId;

    @Getter
    @Setter
    @JsonProperty("chat_id")
    private UUID chatId;

    @Getter
    @Setter
    @JsonProperty("receiver_id")
    private int receiverID;

    @Getter
    @Setter
    @JsonProperty("sender_id")
    private int senderId;


    @Getter
    @Setter
    @JsonProperty("message_content")
    private String messageContent;

    @Getter
    @Setter
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    public Message(UUID messageId, UUID chatId, int receiverID, int senderId, String messageContent) {
        this.messageId = messageId;
        this.chatId = chatId;
        this.receiverID = receiverID;
        this.senderId = senderId;
        this.messageContent = messageContent;
        this.timestamp = LocalDateTime.now();
    }

    public Message( UUID chatId, int receiverID, int senderId, String messageContent) {
        this.messageId = UUID.randomUUID();
        this.chatId = chatId;
        this.receiverID = receiverID;
        this.senderId = senderId;
        this.messageContent = messageContent;
        this.timestamp = LocalDateTime.now();
    }

    public void setMessageId(UUID messageId) {
        this.messageId = UUID.randomUUID();
    }

    public Message() {
        messageId = UUID.randomUUID();
        timestamp = LocalDateTime.now();
    }


}
