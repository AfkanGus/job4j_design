--10. Операторы множества в SQL [#504896]
create table movie
(
    id       serial primary key,
    "name"     text,
    director text
);

create table book
(
    id     serial primary key,
    title  text,
    author text
);

insert into movie (name, director)
values ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

insert into book (title, author)
values ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');
select * from movie;
--- выведите названия всех фильмов, которые сняты по книге.
select name from movie
intersect select title from book;
--- выведите все названия книг, у которых нет экранизации;
select * from book;
select title from book
except
select name from movie;
--- выведите все уникальные названия произведений из таблиц movie и book (т.е фильмы, которые сняты не по книге, и книги без экранизации)
select name from movie
except
select title from book
union all
(select title from book
except select name from movie);