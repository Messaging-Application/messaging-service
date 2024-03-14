package ma.messaging.messagingservice.model;


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
    @Field("message_id")
    private UUID messageId;

    @Getter
    @Setter
    @Field("chat_id")
    private UUID chatId;

    @Getter
    @Setter
    @Field("receiver_id")
    private UUID receiverID;

    @Getter
    @Setter
    @Field("sender_id")
    private UUID senderId;


    @Getter
    @Setter
    @Field("message_content")
    private String messageContent;

    @Getter
    @Setter
    @Field("timestamp")
    private LocalDateTime timestamp;

    public Message(UUID messageId, UUID chatId, UUID receiverID, UUID senderId, String messageContent) {
        this.messageId = messageId;
        this.chatId = chatId;
        this.receiverID = receiverID;
        this.senderId = senderId;
        this.messageContent = messageContent;
        this.timestamp = LocalDateTime.now();
    }

    public Message( UUID chatId, UUID receiverID, UUID senderId, String messageContent) {
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
        timestamp = LocalDateTime.now();
    }


}
