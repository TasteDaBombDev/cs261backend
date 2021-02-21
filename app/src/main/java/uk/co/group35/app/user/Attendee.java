package uk.co.group35.app.user;

import uk.co.group35.app.DBModels.User;

public class Attendee extends User {

    public Attendee(String username, String password) {
        super(username, password);
    }

    public void joinEvent(int eventID) {}

    public void newFeedbackForm() {}

}