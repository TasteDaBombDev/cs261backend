package uk.co.group35.app.events.controllers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.co.group35.app.events.DBModels.LiveEvents;

@Repository
public interface DBDriver extends MongoRepository<LiveEvents, String> {
}
