package uk.co.group35.app.DBModels;

import java.io.Serializable;
import java.util.Objects;

public class EventAttendeeID implements Serializable {

    /**
     * Default serial version ID
     */
    private static final long serialVersionUID = 1L;


    public EventAttendeeID() {}


    private Integer eventID;
    private Integer attendeeID;

    public EventAttendeeID(Integer eventID, Integer attendeeID) {
        this.eventID = eventID;
        this.attendeeID = attendeeID;
    }

    public Integer getEventID() {
        return this.eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getAttendeeID() {
        return this.attendeeID;
    }

    public void setAttendeeID(Integer attendeeID) {
        this.attendeeID = attendeeID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EventAttendeeID)) {
            return false;
        }
        EventAttendeeID eventAttendeeID = (EventAttendeeID) o;
        return Objects.equals(eventID, eventAttendeeID.eventID) && Objects.equals(attendeeID, eventAttendeeID.attendeeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventID, attendeeID);
    }
}