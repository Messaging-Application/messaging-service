package ma.messaging.messagingservice.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "yourDatabaseName"; // Replace with your actual database name
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(new ServerAddress("docdb-2024-03-16-09-49-39.cluster-cb2iswsy4qh7.eu-central-1.docdb.amazonaws.com", 27017))))
                .applyToSocketSettings(builder ->
                        builder.connectTimeout(10, TimeUnit.SECONDS) // Example: Set connect timeout
                                .readTimeout(30, TimeUnit.SECONDS)) // Example: Set read timeout// Enable socket keepAlive
                            .build();

        return MongoClients.create(settings);
    }
}
