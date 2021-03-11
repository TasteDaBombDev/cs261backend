package uk.co.group35.app.RESTcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.group35.app.DBModels.event.PSQLrelated.EventAttendee;
import uk.co.group35.app.requestParams.InviteResponse;
import uk.co.group35.app.utils.PSQL.external.service.EventService;

import java.util.List;

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

    @GetMapping("/attending/{userID}")
    public List<String> getEventsAttending(@PathVariable("userID") Integer userID) {
        return service.getEventsAttending(userID);
    }

    @GetMapping("/invites/pending/{userID}")
    public List<String> getEventsPending(@PathVariable("userID") Integer userID) {
        return service.getEventsPending(userID);
    }

    @PostMapping("/invite/request")
    public ResponseEntity<String> processEventInvite(@RequestBody EventAttendee invite) {
        Integer response = service.processEventInvite(invite);
        // Required field(s) missing in POST request
        if (response == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else if (response == 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User is already attending this event");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
    }

    @PostMapping("/invite/response")
    public ResponseEntity<String> processEventInviteResponse(@RequestBody InviteResponse invite_response) {
        Integer response = service.processEventInviteResponse(invite_response);
        // Required field(s) missing in POST request
        if (response == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else if (response == 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This invite does not exist");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }
    }
    
    
}
