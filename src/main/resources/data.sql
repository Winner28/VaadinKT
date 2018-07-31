CREATE TABLE IF NOT EXISTS Todo(id IDENTITY PRIMARY KEY, done BOOLEAN, text VARCHAR(255));
DELETE FROM Todo;
INSERT INTO Todo VALUES(1, true, 'Prepare presentation');
INSERT INTO Todo VALUES(2, true, 'Procrastinate');
INSERT INTO Todo VALUES(3, false, 'Have presentation');

CREATE TABLE IF NOT EXISTS Users(id IDENTITY PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), money float);
DELETE FROM Users;
INSERT INTO Users VALUES(1, 'Uma', 'Turman', 1000);
