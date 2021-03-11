package uk.co.group35.app.utils.PSQL.external.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.group35.app.DBModels.event.PSQLrelated.EventAttendee;
import uk.co.group35.app.requestParams.InviteResponse;
import uk.co.group35.app.utils.PSQL.external.EventAttendeeRepository;
import uk.co.group35.app.utils.PSQL.external.EventRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class EventService {

    @Autowired
    private EventRepository Events_t;

    @Autowired 
    private EventAttendeeRepository Attending_t;


    /**
     * @return List of event_names of all public events which have not yet started
     */
    public List<String> getAllPublicEvents() {
        return Events_t.findAllPublicEvents();
    }

    /**
     * @param hostID userID of host
     * @return List of event_names of all elapsed events hosted by a particular user
     */
    public List<String> getPastEvents(Integer hostID) {
        return Events_t.findPastEvents(hostID);
    }

    /**
     * @param userID ID of a user
     * @return List of event_names of all events that a user is attending that have not yet started 
     */
    public List<String> getEventsAttending(Integer userID) {
        return Events_t.findEventsAttending(userID);
    }

    /**
     * @param userID ID of a user 
     * @return List of event_names of all event invites that a particular user has not accepted/declined
     */
    public List<String> getEventsPending(Integer userID) {
        return Events_t.findEventsPending(userID);
    }

    /**
     * Stores a new event invite with status 'pending'
     * @param invite New event invite
     * @return -1 for failed request, 0 if invite already exists, 1 for successful save
     */
    public Integer processEventInvite(EventAttendee invite) {
        if (invite.getEventID() == null || invite.getAttendeeID() == null) {
            return -1;
        }
        invite.setStatus("pending");
        try {
            Attending_t.saveAndFlush(invite);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        };
        return 1;
    }

    /**
     * Accepts or rejects an event invite
     * @param invite_response Invite information + attendee response 
     * @return -1 for failed request, 0 if invite does not exist, 1 for success
     */
    public Integer processEventInviteResponse(InviteResponse invite_response) {
        Integer eventID = invite_response.getEventID(), attendeeID = invite_response.getAttendeeID(), response = invite_response.getResponse();
        if (eventID == null || attendeeID == null || response == null) {
            return -1;
        }
        try {
            // Accepted invite
            if (invite_response.getResponse() == 1) {
                Attending_t.acceptInvite(eventID, attendeeID);
            } else { // Rejected invite
                Attending_t.rejectInvite(eventID, attendeeID);
            }
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        };
        return 1;
    }


}