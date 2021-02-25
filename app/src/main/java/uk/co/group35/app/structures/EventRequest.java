package uk.co.group35.app.structures;

import uk.co.group35.app.DBModels.Event;
import uk.co.group35.app.DBModels.LiveEvents;

import java.util.ArrayList;

public class EventRequest {

    private LiveEvents liveEvents;
    private Event eventInfo;


    
    public LiveEvents getLiveEvents() {
        return this.liveEvents;
    }

    public void setLiveEvents(LiveEvents liveEvents) {
        this.liveEvents = liveEvents;
    }
    

    public Event getEventInfo() {
        return this.eventInfo;
    }

    public void setEventInfo(Event event) {
        this.eventInfo = event;
    }


}
