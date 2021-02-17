package uk.co.group35.app.events.controllers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.co.group35.app.events.DBModels.LiveEvents;

/**
 * Database Controller that adds to the DB the data
 */
@Repository
public interface DbRepository extends MongoRepository<LiveEvents, String> {
}
