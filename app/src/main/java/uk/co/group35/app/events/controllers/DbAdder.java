package uk.co.group35.app.events.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uk.co.group35.app.events.enums.FormTemplates;
import uk.co.group35.app.events.DBModels.LiveEvents;

import java.util.Arrays;
import java.util.List;

/**
 * Class that adds to the database and handles the information addition
 * by paths
 */
@Component
public class DbAdder implements CommandLineRunner {

    private static LiveEvents liveEvent = null;
    private DbRepository DBdriver;

    public DbAdder(DbRepository DBRepository) {
        this.DBdriver = DBRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(liveEvent != null) {
            this.DBdriver.deleteAll();
            this.DBdriver.save(liveEvent);
        }
    }

    public static void setLiveEventToBeAdded(Integer EID, FormTemplates[] formTemplates){
        liveEvent = new LiveEvents(EID, formTemplates);
    }

}
