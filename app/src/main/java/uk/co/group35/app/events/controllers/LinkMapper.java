package uk.co.group35.app.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
}
