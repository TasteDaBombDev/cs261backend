package uk.co.group35.app.events.controllers;

import java.util.List;
import uk.co.group35.app.DBModels.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventService {

    @Autowired
    private EventRepository Events_t;

    public List<String> getAllPublicEvents() {
        return Events_t.findAllPublicEvents();
    }

    public List<String> getPastEvents(Integer hostID) {
        return Events_t.findPastEvents(hostID);
    }


}