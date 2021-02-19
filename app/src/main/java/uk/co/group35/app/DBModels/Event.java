package uk.co.group35.app.DBModels;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Events")
public class Event {
    @Id 
    private Integer eventID;
    
    private Integer hostID;
    private String event_name;
    private String start_date;
    private String end_date;
    private String type;

    public Event(Integer eventID, Integer hostID, String event_name, String start_date, String end_date, String type) {
        this.eventID = eventID;
        this.hostID = hostID;
        this.event_name = event_name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.type = type;
    }

    public Integer getEventID() {
        return this.eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getHostID() {
        return this.hostID;
    }

    public void setHostID(Integer hostID) {
        this.hostID = hostID;
    }

    public String getEvent_name() {
        return this.event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}