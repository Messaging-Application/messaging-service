# Messaging Service
This repository contains the messaging microservice codebase, responsible for:
- get messages from SQS
- store them in DocumentDB Mongo database

## Run 

```bash
# Clean the project
mvn clean

# Run the application
mvn spring-boot:run
```

## Run with Docker
```bash
docker-compose up
```

## Run tests
```bash
mvn test
