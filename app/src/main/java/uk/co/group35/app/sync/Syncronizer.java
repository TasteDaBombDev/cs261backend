package uk.co.group35.app.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import uk.co.group35.app.user.controllers.UserRepository;

public class Syncronizer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate template;

    public void createEvent(){

    }
}
