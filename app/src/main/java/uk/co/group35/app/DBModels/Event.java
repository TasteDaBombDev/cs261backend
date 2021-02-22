package uk.co.group35.app.DBModels;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Events")
public class Event {
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "eventID_Sequence")
    @SequenceGenerator(name = "eventID_Sequence", allocationSize = 1)
    @Column(name = "eventID")
    private Integer eventID;
    
    @Column(name = "hostID")
    private Integer hostID;

    @Column(name = "event_name")
    private String event_name;

    @Column(name = "start_date")
    private String start_date;

    @Column(name = "end_date")
    private String end_date;

    @Column(name = "type")
    private String type;

    public Event(Integer hostID, String event_name, String start_date, String end_date, String type) {
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