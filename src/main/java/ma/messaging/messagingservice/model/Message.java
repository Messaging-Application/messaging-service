package ma.messaging.messagingservice.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {

    @Getter
    @Setter
    private UUID messageId;

    @Getter
    @Setter
    private LocalDateTime timestamp;

    @Getter
    @Setter
    private UUID senderId;

    @Getter
    @Setter
    private UUID receiverID;

    @Getter
    @Setter
    private String messageContent;

    public Message(UUID messageId, UUID senderId, UUID receiverID, String messageContent) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverID = receiverID;
        this.messageContent = messageContent;
        timestamp = LocalDateTime.now();
    }

    public Message() {
        timestamp = LocalDateTime.now();
    }
}
