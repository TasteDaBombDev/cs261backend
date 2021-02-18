package uk.co.group35.app.DBModels;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@IdClass(EventAttendeeID.class)
@Table(name = "Users")
public class EventAttendee {

    @Id
    private Integer eventId;

    @Id 
    private Integer attendeeID;

    private String status;


    public EventAttendee(Integer eventId, Integer attendeeID, String status) {
        this.eventId = eventId;
        this.attendeeID = attendeeID;
        this.status = status;
    }

    public Integer getEventId() {
        return this.eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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