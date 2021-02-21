package uk.co.group35.app.events.controllers;

import java.util.List;
import uk.co.group35.app.DBModels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/events")
public class EventController {

    @Autowired
    private EventService service;

    /**
     * @return List of the event_name of all public events in DB
     */
    @GetMapping("/list")
	public List<String> getAllPublicEvents() {
		return service.getAllPublicEvents();
	}

    @GetMapping("/past/{hostID}")
    public List<String> getPastEvents(@PathVariable("hostID") Integer hostID) {
        return service.getPastEvents(hostID);
    }
    
    
}
