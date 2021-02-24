DELETE FROM store.users;
INSERT INTO store.users(userID, username, password) VALUES (13,'mark','password');
INSERT INTO store.users(userID, username, password) VALUES (10,'john','password');
DELETE FROM store.events;
INSERT INTO store.events(eventID, hostID, event_name, start_date, end_date, type)
 VALUES (10, 13, 'Test Event', '2021-02-21 17:21:02.809867', '2021-03-21 17:21:02.809867', 'public');
INSERT INTO store.events(eventID, hostID, event_name, start_date, end_date, type)
 VALUES (15, 13, 'Test Event 2', '2021-02-21 17:21:02.809867', '2021-02-21 17:21:02.909867', 'private');
 