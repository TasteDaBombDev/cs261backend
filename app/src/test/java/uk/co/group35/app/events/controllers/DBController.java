package uk.co.group35.app.events.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.group35.app.events.DBModels.LiveEvents;

import java.util.List;

@RestController
@RequestMapping("/LiveEvents")
public class DBController {

    private DBDriver driver;

    public DBController(DBDriver driver) {
        this.driver = driver;
    }

    @GetMapping("/all")
    public List<LiveEvents> getAll(){
        List<LiveEvents> events = this.driver.findAll();
        return events;
    }
}
