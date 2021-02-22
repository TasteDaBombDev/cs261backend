package uk.co.group35.app.events.controllers;

import uk.co.group35.app.DBModels.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "SELECT event_name FROM Events e WHERE type LIKE 'public'",
        nativeQuery = true)
    List<String> findAllPublicEvents();

    @Query(value = "SELECT event_name FROM Events e WHERE e.hostID = :id and e.end_date < LOCALTIMESTAMP",
        nativeQuery = true)
    List<String> findPastEvents(
        @Param("id") Integer hostID);

    @Query(value = "SELECT event_name FROM Events e WHERE e.eventID IN (SELECT eventID FROM Event_Attendees ea WHERE ea.userID = :id AND ea.status = 'accepted')",
        nativeQuery = true)
    List<String> findEventsAttending(
        @Param("id") Integer userID);
    
}
