CREATE TABLE Surnames (
    surname_id SERIAL PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL
);

CREATE TABLE People (
    person_id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name_id INTEGER REFERENCES Surnames(surname_id) ON DELETE SET NULL
);
INSERT INTO Surnames(last_name) VALUES ('Doe');
INSERT INTO People(first_name, last_name_id) VALUES ('Jane', 1);
SELECT * FROM People;
SELECT * FROM Surnames;