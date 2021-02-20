package uk.co.group35.app.DBModels;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@IdClass(EventAttendeeID.class)
@Table(name = "Event_Attendees")
public class EventAttendee {

    @Id
    private Integer eventID;

    @Id 
    private Integer attendeeID;

    private String status;


    public EventAttendee(Integer eventID, Integer attendeeID, String status) {
        this.eventID = eventID;
        this.attendeeID = attendeeID;
        this.status = status;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

}