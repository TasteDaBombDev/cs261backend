package uk.co.group35.app.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.group35.app.DBModels.FinishedEvent;
import uk.co.group35.app.DBModels.LiveEvents;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates the web paths and interacts with database.
 * Base directory: api/events/...
 */
@RestController
@RequestMapping("api/event")
public class LinkMapper {

    private DBService service;

    public LinkMapper(MongoTemplate template) {
        this.service = new DBService(template);
    }

    @GetMapping("/exit/{eventid}")
    public void closeEvent(@PathVariable("eventid") Integer EID){
        service.closeEvent(EID);
    }

    @GetMapping("/display/{eventid}")
    public List<FinishedEvent> getFinishedEvent(@PathVariable("eventid") Integer EID){
        return service.findFinishedEvent(EID);
    }


    /**
     * HOW TO CALL THIS FUNCTION:
     * GENERALISATION: localhost:8080/api/event/submit/{eventID}/?formValue={value}&text={text}&moment={time}&userID={userID}
     * EXAMPLE: localhost:8080/api/event/submit/1/?formValue=-1.0&text=cool text&moment=17&userID=1
     *
     * @param eventID eventID
     * @param userID userID
     * @param moodScores the mood scores grouped as array
     * @param texts the feedbacks grouped as array
     * @param radioScores radio scores grouped as array
     * @param moment The exact time of the submitted feedback
     * @return A message if everything is good.
     */
    @GetMapping("/submit/{eventid}/")
    public ResponseEntity<String> submitFeedback(@PathVariable("eventid") Integer eventID,
                                       @RequestParam("userID") Integer userID,
                                       @RequestParam("moodScore") Double[] moodScores,
                                       @RequestParam("text") String[] texts,
                                       @RequestParam("radioScore") Integer[] radioScores,
                                       @RequestParam("moment") Double moment){

        service.submitFeedback(eventID, userID, moodScores, texts, radioScores, moment);

        return new ResponseEntity<>("Feedback submitted with success!", HttpStatus.OK);
    }

    @GetMapping("/chart/live/{eventID}")
    public List<Pairs<Double,Double>> constructLiveChart(@PathVariable("eventID") Integer eventID){
        return service.constructLiveChart(eventID);
    }

    @GetMapping("/chart/end/{eventID}")
    public List<Pairs<Double,Double>> constructEndChart(@PathVariable("eventID") Integer eventID){
        return service.constructEndChart(eventID);
    }

    @GetMapping("mood/{eventID}")
    public Double fetchMoodScore(@PathVariable("eventID") Integer eventID){
        return service.fetchMoodScore(eventID);
    }
}
