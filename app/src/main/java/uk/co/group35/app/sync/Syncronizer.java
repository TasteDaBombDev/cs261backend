package uk.co.group35.app.sync;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import uk.co.group35.app.structures.EventRequest;
import uk.co.group35.app.DBModels.Event;

@Service
@Transactional
public class Syncronizer {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private MongoTemplate template;

    public Integer createEvent(Event newEvent){
        return saveNewEvent(newEvent);
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
