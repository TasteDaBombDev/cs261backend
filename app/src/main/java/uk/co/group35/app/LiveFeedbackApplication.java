package uk.co.group35.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"uk.co.group35.app.events.controllers"})
public class LiveFeedbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveFeedbackApplication.class, args);
    }

}
