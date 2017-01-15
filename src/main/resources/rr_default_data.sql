\c restaurant_reservations;

INSERT INTO RESTAURANT_TABLE (ID, CAPACITY) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 4),
(5, 4),
(6, 4),
(7, 4),
(8, 4),
(9, 4),
(10, 8),
(11, 8),
(12, 8),
(13, 8),
(14, 8);

INSERT INTO GUEST (ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, REGISTERED, CREATED_DATE, UPDATED_DATE) VALUES
(1, 'John', 'Doe', null, false, LOCALTIMESTAMP, LOCALTIMESTAMP),
(2, 'Jane', 'Doe', 'jdoe@gmail.com', true, LOCALTIMESTAMP, LOCALTIMESTAMP),
(3, 'Mike', 'Smith', 'msmith@gmail.com', true, LOCALTIMESTAMP, LOCALTIMESTAMP),
(4, 'John', 'Public', 'jqpublic@gmail.com', true, LOCALTIMESTAMP, LOCALTIMESTAMP),
(5, 'Mary', 'Moore', null, false, LOCALTIMESTAMP, LOCALTIMESTAMP),
(6, 'Don', 'Jones', 'djones@hotmail.com', true, LOCALTIMESTAMP, LOCALTIMESTAMP);

INSERT INTO RESERVATION (ID, RESTAURANT_TABLE_ID, GUEST_ID, START_TIME, END_TIME, CREATED_DATE, UPDATED_DATE) VALUES
(1, 4, 1, TO_TIMESTAMP('2017-01-17 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-17 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), LOCALTIMESTAMP, LOCALTIMESTAMP),
(2, 4, 2, TO_TIMESTAMP('2017-01-17 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-17 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), LOCALTIMESTAMP, LOCALTIMESTAMP),
(3, 1, 3, TO_TIMESTAMP('2017-01-17 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-17 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), LOCALTIMESTAMP, LOCALTIMESTAMP),
(4, 10, 4, TO_TIMESTAMP('2017-01-17 11:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-17 12:45:00', 'YYYY-MM-DD HH24:MI:SS'), LOCALTIMESTAMP, LOCALTIMESTAMP),
(5, 5, 5, TO_TIMESTAMP('2017-01-17 13:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-17 14:45:00', 'YYYY-MM-DD HH24:MI:SS'), LOCALTIMESTAMP, LOCALTIMESTAMP),
(6, 2, 6, TO_TIMESTAMP('2017-01-17 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-01-17 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), LOCALTIMESTAMP, LOCALTIMESTAMP);
