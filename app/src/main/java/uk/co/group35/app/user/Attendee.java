package uk.co.group35.app.user;

import uk.co.group35.app.user.DBModels.User;

public class Attendee extends User {

    public Attendee(Integer id, String username, String password) {
        super(id, username, password);
    }

    public void joinEvent(int eventID) {}

    public void newFeedbackForm() {}

}