package uk.co.group35.app.events.controllers;

import uk.co.group35.app.DBModels.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Integer> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE Event_Attendees ea SET status = 'accepted' WHERE ea.eventID = :eventID AND ea.userID = :attendeeID",
        nativeQuery = true)
    void acceptInvite(
        @Param("eventID") Integer eventID, 
        @Param("attendeeID") Integer attendeeID);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM Event_Attendees ea WHERE ea.eventID = :eventID AND ea.userID = :attendeeID",
        nativeQuery = true)
    void rejectInvite(
        @Param("eventID") Integer eventID, 
        @Param("attendeeID") Integer attendeeID);

    
}
