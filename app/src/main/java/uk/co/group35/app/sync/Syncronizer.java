package uk.co.group35.app.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import uk.co.group35.app.DBModels.event.PSQLrelated.Event;
import uk.co.group35.app.DBModels.event.MongoDBrelated.FormTemplates;
import uk.co.group35.app.DBModels.event.MongoDBrelated.LiveEvents;
import uk.co.group35.app.DBModels.enums.FormTypes;
import uk.co.group35.app.utils.general.Pairs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class Syncronizer {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MongoTemplate template;

    /**
     * CREATES AN EVENT BY SYNCRONISING THE PSQL WITH MONGO
     * @param newEvent the event that have to be added to PSQL
     * @param questions the questions array
     * @param formsType the questions type array
     */
    public void createEvent(Event newEvent, String[] questions, Integer[] formsType){

        Integer eventID = saveNewEvent(newEvent);
        int i = 0;

        ArrayList<FormTemplates> forms = new ArrayList<>();

        for (Integer formType : formsType){
            switch (formType){
                case 0: { // MOOD SLIDER
                    FormTemplates f = new FormTemplates(new Pairs<>(FormTypes.SLIDER_FORM, questions[i]));
                    forms.add(f);
                } break;

                case 1: { // RADIO BUTTON
                    FormTemplates f = new FormTemplates(new Pairs<>(FormTypes.STEPS_FORM, questions[i]));
                    forms.add(f);
                } break;

                case 2: { // FEEDBACK FORM
                    FormTemplates f = new FormTemplates(new Pairs<>(FormTypes.COMMENT_BLOCK_FORM, questions[i]));
                    forms.add(f);
                } break;
            }
            i++;
        }

        LiveEvents l = new LiveEvents(eventID,forms, new ArrayList<>());
        template.save(l);
    }

    /**
     * Inserts the new event into the Events table and returns its eventID
     */
    public Integer saveNewEvent(Event newEvent) {
        return (Integer) em.createNativeQuery("INSERT INTO Events(eventID, hostID, event_name, start_date, end_date, type) " 
        + "VALUES (nextval('eventID_sequence'), :hostID, :event_name, TO_TIMESTAMP(:start_date, 'YYYY-MM-DD HH:MI:SS'), "
        + "TO_TIMESTAMP(:end_date, 'YYYY-MM-DD HH:MI:SS'), :type) "
        + "RETURNING eventID")
        .setParameter("hostID", newEvent.getHostID()) 
        .setParameter("event_name", newEvent.getEvent_name())
        .setParameter("start_date", newEvent.getStart_date())
        .setParameter("end_date", newEvent.getEnd_date())
        .setParameter("type", newEvent.getType())
        .getSingleResult();
    }




}
