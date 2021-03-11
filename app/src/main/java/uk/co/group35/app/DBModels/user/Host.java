package uk.co.group35.app.DBModels.user;

public class Host extends User {

    public Host(String username, String password) {
        super(username, password);
    }

    public void setupEvent(Integer eventID, String event_name, String start_date, String end_date) {}

    public void getFeedback(Integer eventID) {}

}