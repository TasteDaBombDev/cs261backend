package uk.co.group35.app.DBModels;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@IdClass(EventAttendeeID.class)
@Table(name = "Event_Attendees")
public class EventAttendee {

    @Id
    @Column(name = "eventID")
    private Integer eventID;

    @Id 
    @Column(name = "attendeeID")
    private Integer attendeeID;

    @Column(name = "status")
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