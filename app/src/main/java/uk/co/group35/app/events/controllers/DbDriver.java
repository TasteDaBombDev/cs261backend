package uk.co.group35.app.events.controllers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uk.co.group35.app.DBModels.LiveEvents;

/**
 * Database Controller that adds to the DB the data
 */
@Repository
public interface DbDriver extends MongoRepository<LiveEvents, String> {

}
