package uk.co.group35.app.user.DBModels;

public class Host extends User {

    public Host(Long id, String username, String password) {
        super(id, username, password);
    }

    public void setupEvent(int eventID, String event_name, String start_date, String end_date) {}

    public void getFeedback(int eventID) {}

}