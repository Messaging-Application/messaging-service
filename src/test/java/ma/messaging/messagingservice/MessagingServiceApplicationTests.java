package ma.messaging.messagingservice;

import com.amazonaws.services.sqs.AmazonSQS;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MessagingServiceApplicationTests {

    @MockBean
    private AmazonSQS amazonSQS;

    @Test
    void contextLoads() {
    }

}
