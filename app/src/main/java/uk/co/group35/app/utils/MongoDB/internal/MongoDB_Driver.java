package uk.co.group35.app.utils.MongoDB.internal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.co.group35.app.DBModels.event.MongoDBrelated.LiveEvents;

/**
 * Database Controller that adds to the DB the data
 */
@Repository
public interface MongoDB_Driver extends MongoRepository<LiveEvents, String> {

}
