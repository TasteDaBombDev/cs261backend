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

        FormTemplates t1 = new FormTemplates(new Pairs<>(FormTypes.SLIDER_FORM, "ce faci?"));
        FormTemplates t2 = new FormTemplates(new Pairs<>(FormTypes.STEPS_FORM, "iti place?"));
        ArrayList<FormTemplates> forms = new ArrayList<>();
        forms.add(t1);
        forms.add(t2);

        List<LiveEvents> l = new ArrayList<>();

        l.add(new LiveEvents(1,forms, new ArrayList<>()));
        l.add(new LiveEvents(2,forms, new ArrayList<>()));
        l.add(new LiveEvents(3,forms, new ArrayList<>()));
        l.add(new LiveEvents(4,forms, new ArrayList<>()));


        this.DBdriver.deleteAll();
        this.DBdriver.saveAll(l);

    }

}
