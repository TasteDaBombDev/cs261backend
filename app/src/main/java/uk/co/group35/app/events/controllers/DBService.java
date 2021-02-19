package uk.co.group35.app.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import uk.co.group35.app.DBModels.FinishedEvent;
import uk.co.group35.app.DBModels.LiveEvents;
import uk.co.group35.app.DBModels.UserFeedback;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;
import java.util.List;

public class DBService {

    @Autowired
    private MongoTemplate template;

    public DBService(MongoTemplate template) {
        this.template = template;
    }

    public void closeEvent(Integer EID){

        List<LiveEvents> events = template.find(new Query().addCriteria(Criteria.where("EID").is(EID)), LiveEvents.class);

        if(!events.isEmpty()) {
            LiveEvents event = events.get(0);

            ArrayList<UserFeedback> feedbacks = event.getAllUserFeedbacks();
            ArrayList<Pairs<Double, Double>> times = new ArrayList<>();

            ArrayList<String> keywords = new ArrayList<>();

            for (UserFeedback feedback : feedbacks) {
                times.add(new Pairs<>(feedback.getMomentSent(), feedback.getMoodscore()));
                keywords.addAll(feedback.getKeywords());
            }

            FinishedEvent finishedEvent = new FinishedEvent(
                    event.getEID(),
                    event.getMoodScore(),
                    keywords,
                    times
            );

            template.remove(new Query().addCriteria(Criteria.where("EID").is(EID)), LiveEvents.class);
            template.save(finishedEvent);
        }
    }

    public List<FinishedEvent> findFinishedEvent(Integer EID){
        return template.find(new Query().addCriteria(Criteria.where("EID").is(EID)), FinishedEvent.class);
    }
}
