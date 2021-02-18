CREATE SCHEMA IF NOT EXISTS store;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users(
    userID INTEGER PRIMARY KEY,
    username  VARCHAR(40) NOT NULL,
    password VARCHAR(40) NOT NULL
);

DROP TABLE IF EXISTS Events;
CREATE TABLE Events(
    eventID INTEGER PRIMARY KEY,
    hostID  INTEGER NOT NULL,
    event_name VARCHAR(40) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    -- private/public
    type VARCHAR(10),
    FOREIGN KEY(hostID) REFERENCES Users(userID)
);

DROP TABLE IF EXISTS Event_Attendees;
CREATE TABLE Event_Attendees(
    userID INTEGER,
    eventID  INTEGER,
    -- pending/accepted
    status VARCHAR(10), 
    PRIMARY KEY(userID, eventID),
    FOREIGN KEY(userID) REFERENCES Users(userID),
    FOREIGN KEY(eventID) REFERENCES Events(eventID)
);