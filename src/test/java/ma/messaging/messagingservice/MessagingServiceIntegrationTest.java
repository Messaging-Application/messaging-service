package ma.messaging.messagingservice;

import com.amazonaws.services.sqs.AmazonSQS;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import ma.messaging.messagingservice.model.Message;
import ma.messaging.messagingservice.repository.MessageRepository;
import ma.messaging.messagingservice.service.SQSService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class MessagingServiceIntegrationTest {

    @MockBean
    private AmazonSQS amazonSQS;

    @InjectMocks
    private SQSService sqsService;


    @Autowired
    private MessageRepository messageRepository;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp(){
        RestAssured.port = port;
    }

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void testStoreAndRetrieveMessage() {
        UUID chatId = UUID.randomUUID();
        Message message = new Message(UUID.randomUUID(), chatId, 3, 2, "Hello, Testcontainers!");
        messageRepository.save(message);

        List<Message> retrievedMessages = messageRepository.findByChatId(chatId);

        assertFalse(retrievedMessages.isEmpty());
        assertEquals(message.getMessageContent(), retrievedMessages.get(0).getMessageContent());
    }

    @Test
    void testGetMessagesByChatId() {
        UUID chatId = UUID.randomUUID();
        Message message = new Message(UUID.randomUUID(), chatId, 1, 2, "Hello from Testcontainers!");
        messageRepository.save(message);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/message/{chatId}", chatId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", greaterThan(0))
                .body("[0].message_content", equalTo("Hello from Testcontainers!"));
    }
}
