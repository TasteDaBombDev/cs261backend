package uk.co.group35.app.events.controllers;

import uk.co.group35.app.DBModels.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "SELECT event_name FROM Events e WHERE type LIKE 'public'",
        nativeQuery = true)
    List<String> findAllPublicEvents();

    @Query(value = "SELECT event_name FROM Events e WHERE e.hostID = :id and e.end_date < LOCALTIMESTAMP",
        nativeQuery = true)
    List<String> findPastEvents(
        @Param("id") Integer hostID);
    
}
