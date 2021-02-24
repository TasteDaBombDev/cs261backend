package uk.co.group35.app.events;

public class InviteResponse {
    private Integer eventID;
    private Integer attendeeID;
    private Integer response;

    public InviteResponse(Integer eventID, Integer attendeeID, Integer response) {
        this.eventID = eventID;
        this.attendeeID = attendeeID;
        this.response = response;
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

    public Integer getResponse() {
        return this.response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    
}
