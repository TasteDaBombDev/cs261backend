package uk.co.group35.app.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.group35.app.DBModels.LiveEvents;

import java.util.List;

/**
 * Class that creates the web paths and interacts with database.
 * Base directory: api/events/...
 */
@RestController
@RequestMapping("api/LiveEvents")
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

}
