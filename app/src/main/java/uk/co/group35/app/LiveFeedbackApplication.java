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
/*

(done) login /api/users/login - POSTGRESQL
(done) register /api/users/register - POSTGRESQL

--USERPAGE--

(done) fetch past host events /api/events/past/{userid} - POSTGRESQL
(done) list all events that the user will go /api/events/attending/{userid} - POSTGRESQL
(done) accept/deny the events (join event for public) /api/events/invites/{userid} - POSTGRESQL
(done) list all public events that they have not register (filters) /api/events/list - POSTGRESQL

see previous events (host only) + analytics /api/events/display/{eventid} - MONGO
joins events /api/event/join/{eventid},{userid} - POSTGRESQL (check if the user is attending to that particular event) MONGO
exits events /api/events/exit/{eventid} - MONGO

--EVENT_CREATION--

create event /api/event/create/{userid,eventName,eventStartDate,eventStopDate,questions + template type} - POSTGRESQL,MONGO - Sync
(done) list all the users /api/users/list - POSTGRESQL

--LIVE_Feedback_FORM-

submit a feedback /api/submit/{userid},{eventid} - MONGO

--LIVE_EVENT_PAGE-
fetch_data for the event /api/events/data/{eventid} - MONGO


*/
