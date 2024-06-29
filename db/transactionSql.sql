--4.4. Транзакции. Работа с транзакциями в PostgreSQL [#504813].
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
--запустим транзакцию.
begin transaction;
select * from person;
--вставим еще одну строку в таблицу.
insert into person (name, surname, age, weight) VALUES ('ivan_4', 'petrov_4', 30, 70);
 --добавим точку сохраниения.
 savepoint firstsavepoint;
 --далее выполним удаления.
 delete from person;
 --вставим еще одну строку в таблицу.
insert into person (name, surname, age, weight) VALUES ('ivan_5', 'petrov_5', 40, 80);
--добавим вторую точку сохранения.
savepoint secondsavepoint;
 select * from person;
 --удалим польностью таблицу.
 drop table person;
 --прервем транзакцию до точки сохранения.
rollback to secondsavepoint;
select * from person;
 rollback to firstsavepoint;
 --зафиксируем все изменения.
commit;
--востановили таблицу через сохраненую точку.
select * from person;