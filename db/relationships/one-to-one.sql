CREATE TABLE users(
id SERIAL PRIMARY KEY,
name VARCHAR(255)
);

CREATE TABLE accounts(
id SERIAL PRIMARY KEY,
login VARCHAR(255),
password VARCHAR(255)
);

CREATE TABLE users_accounts(
id SERIAL PRIMARY KEY,
users_id INT REFERENCES users(id) UNIQUE,
account_id INT REFERENCES users(id) UNIQUE
);

INSERT INTO users(name)
VALUES('Ivan Ivanov'), ('Petr Petrov');

INSERT INTO accounts (login, password)
VALUES ('12345', 'pas123'), ('123', 'myPas123');

INSERT INTO users_accounts(users_id, account_id)
VALUES (1,2), (2,1);

SELECT * FROM users;

SELECT * FROM accounts;

SELECT * FROM users_accounts;

