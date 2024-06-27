--4.3. Транзакции. Уровни изолированности транзакций. [#504812 #501341].
create table person
(
    id       serial primary key,
    name     varchar(50),
    surname varchar(50),
    age    integer default 0,
    weight   integer
);
insert into person (name, surname, age, weight)
VALUES ('ivan', 'petrov', 10, 15);
insert into person (name, surname, age, weight)
VALUES ('ivan_2', 'petrov_2', 15, 30);
insert into person (name, surname, age, weight)
VALUES ('ivan_3', 'petrov_3', 20, 60);