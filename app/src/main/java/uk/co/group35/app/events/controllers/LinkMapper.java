package uk.co.group35.app.events.controllers;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.group35.app.DBModels.Event;
import uk.co.group35.app.DBModels.FinishedEvent;
import uk.co.group35.app.DBModels.FormTemplates;
import uk.co.group35.app.structures.Pairs;
import uk.co.group35.app.sync.Syncronizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class that creates the web paths and interacts with database.
 * Base directory: api/events/...
 */
@RestController
@RequestMapping("api/event")
public class LinkMapper {

    private DBService service;

    @Autowired 
    private Syncronizer syncService;

    public LinkMapper(MongoTemplate template) {
        this.service = new DBService(template);
    }

    /**
     * Close an EVENT
     * @param EID eventID
     */
    @GetMapping("/live/exit/{eventid}")
    public void closeEvent(@PathVariable("eventid") Integer EID){
        service.closeEvent(EID);
    }

    /**
     * Displays an finished event
     * @param EID eventID
     * @return Details about the finished event
     */
    @GetMapping("/end/display/{eventid}")
    public List<FinishedEvent> getFinishedEvent(@PathVariable("eventid") Integer EID){
        return service.findFinishedEvent(EID);
    }


    /**
     * HOW TO CALL THIS FUNCTION:
     * GENERALISATION: localhost:8080/api/event/submit/{eventID}/?formValue={value1,value2,value3}&text={text1,text2,text3}&moment={time}&userID={userID}
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
    @GetMapping("/live/submit/{eventid}/")
    public ResponseEntity<String> submitFeedback(@PathVariable("eventid") Integer eventID,
                                       @RequestParam("userID") Integer userID,
                                       @RequestParam("moodScore") Double[] moodScores,
                                       @RequestParam("text") String[] texts,
                                       @RequestParam("radioScore") Integer[] radioScores,
                                       @RequestParam("moment") Double moment){

        service.submitFeedback(eventID, userID, moodScores, texts, radioScores, moment);

        return new ResponseEntity<>("Feedback submitted with success!", HttpStatus.OK);
    }

    @GetMapping("/send/data")
    public List<String> listSentiment(@RequestParam("text") String text){
        List<String> s = new ArrayList<>();

        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

        CoreDocument coreDocument = new CoreDocument(text);
        pipeline.annotate(coreDocument);

        List<CoreSentence> sentences = coreDocument.sentences();

        for(CoreSentence sentence : sentences) {

            String sentiment = sentence.sentiment();

            s.add(sentiment + " - " + sentence);
        }

        return s;


    }

    @GetMapping("/live/chart/{eventID}")
    public List<Pairs<Double,Double>> constructLiveChart(@PathVariable("eventID") Integer eventID){
        return service.constructLiveChart(eventID);
    }

    /**
     * REETURNS A LIST OF PAIRS THAT CONTAIN THE MOMENT AND THE VALUE OF THE MOOD
     * @param eventID the event ID
     * @return
     */
    @GetMapping("/end/chart/{eventID}")
    public List<Pairs<Double,Double>> constructEndChart(@PathVariable("eventID") Integer eventID){
        return service.constructEndChart(eventID);
    }

    /**
     * REFRESHES THE MOOD VALUE FOR HOST
     * @param eventID the event ID
     * @return
     */
    @GetMapping("/live/mood/{eventID}")
    public Double fetchMoodScore(@PathVariable("eventID") Integer eventID){
        return service.fetchMoodScore(eventID);
    }

    /**
     * REFRESHES THE KEYWORDS
     * @param eventID the event ID
     * @return
     */
    @GetMapping("/live/keywords/{eventID}")
    public List<String> fetchKeywordsLive(@PathVariable("eventID") Integer eventID){
        return service.fetchKeywords(eventID);
    }

    /**
     * THE FUNCTION THAT SENDS THE USER THE FEEDBACK FORM AND QUESTIONS
     * @param eventID the event ID
     * @return a list of forms
     */
    @GetMapping("/live/user/join/{eventID}")
    public List<FormTemplates> joinUserToEvent(@PathVariable("eventID") Integer eventID){
        return service.getTemplatesFromEvent(eventID);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestParam("hostID") Integer hostID,
                                              @RequestParam("event_name") String eventName,
                                              @RequestParam("start_date") String startDate,
                                              @RequestParam("end_date") String endDate,
                                              @RequestParam("type") String type,
                                              @RequestParam("questions") String[] questions,
                                              @RequestParam("formTypes") Integer[] formTypes) {

        Event newEvent = new Event(hostID, eventName, startDate, endDate, type);
        syncService.createEvent(newEvent, questions, formTypes);
        return new ResponseEntity<>("Event succesfully created", HttpStatus.OK);

    }
}
