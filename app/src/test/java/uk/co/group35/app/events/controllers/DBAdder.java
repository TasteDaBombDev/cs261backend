package uk.co.group35.app.events.controllers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uk.co.group35.app.events.DBModels.LiveEvents;
import uk.co.group35.app.events.enums.FormTemplates;

import java.util.Arrays;
import java.util.List;

@Component
public class DBAdder implements CommandLineRunner {

//    private LiveEvents liveEvents;
    private DBDriver DBdriver;

    public DBAdder(DBDriver DBDriver) {
        this.DBdriver = DBDriver;
    }

    @Override
    public void run(String... args) throws Exception {
        LiveEvents liveEvents = new LiveEvents(
                1,
                new FormTemplates[]{FormTemplates.SLIDER_FORM, FormTemplates.COMMENT_BLOCK_FORM}
        );

        LiveEvents liveEvents2 = new LiveEvents(
                2,
                new FormTemplates[]{FormTemplates.SLIDER_FORM, FormTemplates.COMMENT_BLOCK_FORM}
        );
        LiveEvents liveEvents3 = new LiveEvents(
                3,
                new FormTemplates[]{FormTemplates.SLIDER_FORM, FormTemplates.COMMENT_BLOCK_FORM}
        );
        LiveEvents liveEvents4 = new LiveEvents(
                4,
                new FormTemplates[]{FormTemplates.SLIDER_FORM, FormTemplates.COMMENT_BLOCK_FORM}
        );

        this.DBdriver.deleteAll();
        List<LiveEvents> liveEventsList = Arrays.asList(liveEvents,liveEvents2,liveEvents3,liveEvents4);
        this.DBdriver.saveAll(liveEventsList);
    }

    public void setLiveEventToBeAdded(Integer EID, FormTemplates[] formTemplates){
//        liveEvents = new LiveEvents(EID, formTemplates);
    }

}
