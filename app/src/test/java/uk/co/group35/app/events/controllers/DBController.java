package uk.co.group35.app.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.group35.app.events.DBModels.LiveEvents;

import java.util.List;

@RestController
@RequestMapping("/LiveEvents")
public class DBController {

    @Autowired
    private DBDriver DBdriver;

    public DBController(DBDriver driver) {
        this.DBdriver = driver;
    }

    @GetMapping("/all")
    public List<LiveEvents> getAll(){
        List<LiveEvents> events = this.DBdriver.findAll();
        return events;
    }
}
