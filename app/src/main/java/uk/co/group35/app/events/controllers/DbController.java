package uk.co.group35.app.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.group35.app.DBModels.LiveEvents;

import java.util.List;

/**
 * Class that creates the web paths and interacts with database.
 * Base directory: api/events/...
 */
@RestController
@RequestMapping("api/event")
public class DbController {

    @Autowired
    private DbRepository DBdriver;

    public DbController(DbRepository driver) {
        this.DBdriver = driver;
    }

    @GetMapping("/all")
    public List<LiveEvents> getAll(){
        List<LiveEvents> events = this.DBdriver.findAll();
        return events;
    }

    @GetMapping("/exit/{eventid}")
    public String closeEvent(@PathVariable("eventid") Integer EID){
//        this.DBdriver.find();
        return String.valueOf(EID);
    }
}
