--1. Представления [#504792].

  create table car_bodies (
    id int primary key ,
    "name" text
);

create table car_engines (
    id int primary key ,
   "name" text
);

create table car_transmissions (
    id int primary key ,
    "name" text
);

create table car(
id int primary key,
"name" text,
 body_id int references car_bodies (id),
 engine_id int references car_engines(id),
 transmission_id int references car_transmissions(id)
);
insert into car_bodies(id, "name") values
(1, 'Седан'),
(2, 'Хэтчбэк'),
(3, 'Универсал'),
(4, 'Пикап');

insert into car_engines(id,"name") values
(1, 'Газ'),
(2, 'Дизель'),
(3, 'Электро'),
(4, 'Гибрид');

insert into car_transmissions(id,"name") values
(1,'Механика'),
(2,'Автомат'),
(3,'5 ступенчатый автомат'),
(4,'Гибрит робот');

insert into   car (id, name, body_id, engine_id, transmission_id) values
(1, 'Toyota Camry', 1, 1, 2),
(2, 'Honda', 2, 2, 2),
(3, 'Volkswagen Golf', 3, 1, 1),
(4, 'BMW 3', 1, 3, 2),
(5, 'Tesla S', 1, 3, 3),
(6,'Vaz 3110',null,null ,1);

--Представление что бы вывести кузова, которые не используются НИ в одной машине(id -4, Пикап).
   create view body_nameunuse_view as
      select
    cb.id,
    cb.name as body_nameunuse
from
    car_bodies cb
left join
    car c on cb.id = c.body_id
where
    c.id is null
    group by cb.id;
    -- запрос с помощью данного представления.
    select * from body_nameunuse_view;

ALTER VIEW body_nameunuse_view RENAME TO body_nameunuse_view1;
