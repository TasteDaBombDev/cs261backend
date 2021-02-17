DROP TABLE IF EXISTS Users;
CREATE TABLE Events(
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
    FOREIGN KEY(hostID) REFERENCES Users(userID)
);

DROP TABLE IF EXISTS Event_Attendees;
CREATE TABLE Event_Attendees(
    userID INTEGER,
    eventID  INTEGER,
    PRIMARY KEY(userID, eventID),
    FOREIGN KEY(userID) REFERENCES Users(userID),
    FOREIGN KEY(eventID) REFERENCES Events(eventID)
);