package uk.co.group35.app.RESTcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.group35.app.DBModels.event.PSQLrelated.Event;
import uk.co.group35.app.DBModels.event.MongoDBrelated.FinishedEvent;
import uk.co.group35.app.DBModels.event.MongoDBrelated.FormTemplates;
import uk.co.group35.app.requestParams.CreateParams;
import uk.co.group35.app.requestParams.SubmitFeedbackParams;
import uk.co.group35.app.utils.MongoDB.external.service.DBService;
import uk.co.group35.app.utils.general.Pairs;
import uk.co.group35.app.sync.Syncronizer;

import java.util.List;

/**
 * Class that creates the web paths and interacts with database.
 * Base directory: api/events/...
 */
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("api/event")
public class MongoEventController {

    private final DBService service;

    @Autowired 
    private Syncronizer syncService;

    public MongoEventController(MongoTemplate template) {
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
     * @param param the params in the request body
     * @return A message if everything is good.
     */
    @GetMapping("/live/submit/{eventid}/")
    public ResponseEntity<String> submitFeedback(@PathVariable("eventid") Integer eventID,
                                                 @RequestBody SubmitFeedbackParams param){

        if(param.getMoodScores().length > 0 || param.getTexts().length > 0 || param.getRadioScores().length > 0){
            service.submitFeedback(eventID, param.getUserID(),
                    param.getMoodScores(), param.getTexts(), param.getRadioScores(),
                    param.getMoment());
        }

        return new ResponseEntity<>("Feedback submitted with success!", HttpStatus.OK);
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
    public ResponseEntity<String> createEvent(@RequestBody CreateParams params) {

        Event newEvent = new Event(params.getHostID(), params.getEventName(), params.getStartDate(), params.getEndDate(), params.getType());
        syncService.createEvent(newEvent, params.getQuestions(), params.getFormTypes());
        return new ResponseEntity<>("Event successfully created", HttpStatus.OK);

    }
}
