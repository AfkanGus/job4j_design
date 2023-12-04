create table Users (
    user_id serial primary key,
    username varchar(255)
);

create table Groups (
    group_id serial primary key ,
    group_name varchar(255)
);

create table UsersGroups (
    id serial primary key,
    user_id int references Users(user_id),
    group_id int references Groups(group_id)
);
INSERT INTO Users (username) VALUES ('Alice'), ('Bob'), ('Charlie');
INSERT INTO Groups (group_name) VALUES ('Admins'), ('Editors'), ('Readers');
INSERT INTO UsersGroups (user_id, group_id) VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3);
SELECT * FROM Users;
SELECT * FROM Groups;
SELECT * FROM UsersGroups;
