package uk.co.group35.app.events.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uk.co.group35.app.DBModels.FormTemplates;
import uk.co.group35.app.DBModels.LiveEvents;
import uk.co.group35.app.DBModels.enums.FormTypes;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;

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

//        FormTemplates t1 = new FormTemplates(new Pairs<>(FormTypes.SLIDER_FORM, "ce faci?"));
//        FormTemplates t2 = new FormTemplates(new Pairs<>(FormTypes.STEPS_FORM, "iti place?"));
//        ArrayList<FormTemplates> forms = new ArrayList<>();
//        forms.add(t1);
//        forms.add(t2);
//        LiveEvents l = new LiveEvents(1,forms);

        if(liveEvent != null) {
            this.DBdriver.deleteAll();
            this.DBdriver.save(liveEvent);
        }
    }

    public static void setLiveEventToBeAdded(Integer EID, ArrayList<FormTemplates> formTemplates){
        liveEvent = new LiveEvents(EID, formTemplates);
    }

}
