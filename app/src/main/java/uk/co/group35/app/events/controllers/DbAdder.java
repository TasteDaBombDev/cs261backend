package uk.co.group35.app.events.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uk.co.group35.app.DBModels.FormTemplates;
import uk.co.group35.app.DBModels.LiveEvents;
import uk.co.group35.app.DBModels.UserFeedback;
import uk.co.group35.app.DBModels.enums.FormTypes;
import uk.co.group35.app.structures.Pairs;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that adds to the database
 */
@Component
public class DbAdder implements CommandLineRunner {

    private DbDriver DBdriver;

    public DbAdder(DbDriver DBDriver) {
        this.DBdriver = DBDriver;
    }

    @Override
    public void run(String... args) throws Exception {

        ArrayList<String> k = new ArrayList<>();
        k.add("Friend");
        k.add("Nice");
        k.add("Friendly");
        k.add("Bad");
        k.add("Pretty good");

        UserFeedback f1 = new UserFeedback(1,80.0,k,1.0);
        UserFeedback f2 = new UserFeedback(2,88.0,k,2.5);
        ArrayList<UserFeedback> feedback = new ArrayList<>();
        feedback.add(f1);
        feedback.add(f2);

        FormTemplates t1 = new FormTemplates(new Pairs<>(FormTypes.SLIDER_FORM, "ce faci?"));
        FormTemplates t2 = new FormTemplates(new Pairs<>(FormTypes.STEPS_FORM, "iti place?"));
        ArrayList<FormTemplates> forms = new ArrayList<>();
        forms.add(t1);
        forms.add(t2);

        List<LiveEvents> l = new ArrayList<>();

        l.add(new LiveEvents(1,forms, feedback));
        l.add(new LiveEvents(2,forms, feedback));
        l.add(new LiveEvents(3,forms, feedback));
        l.add(new LiveEvents(4,forms, feedback));


        this.DBdriver.deleteAll();
        this.DBdriver.saveAll(l);

    }

}
