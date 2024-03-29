package uk.co.group35.app.utils.MongoDB.external.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import uk.co.group35.app.DBModels.event.MongoDBrelated.FinishedEvent;
import uk.co.group35.app.DBModels.event.MongoDBrelated.FormTemplates;
import uk.co.group35.app.DBModels.event.MongoDBrelated.LiveEvents;
import uk.co.group35.app.DBModels.event.MongoDBrelated.UserFeedback;
import uk.co.group35.app.ml.Analyzer;
import uk.co.group35.app.utils.general.Pairs;

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

    public void submitFeedback(Integer eventID, Integer userID,
                               Double[] moodScores, String[] texts, Integer[] radioScores,
                               Double time) {

        //check the moodscroe value it starts with 0

        Analyzer analyzer = new Analyzer();
        Pairs<Double, ArrayList<String>> result = analyzer.analyze(moodScores, texts, radioScores);

        UserFeedback userFeedback = new UserFeedback(userID, result.getKey(), result.getValue(), time);

        List<LiveEvents> events = template.find(new Query().addCriteria(Criteria.where("EID").is(eventID)), LiveEvents.class);
        LiveEvents e = events.get(0);
        e.addFeedback(userFeedback);

        System.out.println("============================================");

        template.remove(new Query().addCriteria(Criteria.where("EID").is(eventID)), LiveEvents.class);
        template.save(e);
    }

    public List<Pairs<Double,Double>> constructLiveChart(Integer eventID){
        return template.find(new Query().addCriteria(Criteria.where("EID").is(eventID)), LiveEvents.class).get(0).generateChart();
    }

    public List<Pairs<Double,Double>> constructEndChart(Integer eventID){
        return template.find(new Query().addCriteria(Criteria.where("EID").is(eventID)), FinishedEvent.class).get(0).getTimes();
    }

    public Double fetchMoodScore(Integer ID){
        return template.find(new Query().addCriteria(Criteria.where("EID").is(ID)), LiveEvents.class).get(0).getMoodScore();
    }

    public List<FormTemplates> getTemplatesFromEvent(Integer eventID){
        return template.find(new Query().addCriteria(Criteria.where("EID").is(eventID)), LiveEvents.class).get(0).getTemplates();
    }

    public List<String> fetchKeywords(Integer eventID){
        return template.find(new Query().addCriteria(Criteria.where("EID").is(eventID)), LiveEvents.class).get(0).getKeywords();
    }
}
