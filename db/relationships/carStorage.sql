--1. Хранилище машин [#1733].
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

delete from car;

--Вывести список всех машин и все привязанные к ним детали.
select
  c.id,
  c.name as car_name,
  cb.name as body_name,
  ce.name as engine_name,
  ct.name as transmission_name
from car c
left join
    car_bodies cb on c.body_id = cb.id
left join
    car_engines ce on c.engine_id = ce.id
left join
    car_transmissions ct on c.transmission_id = ct.id;;


--Вывести кузова, которые не используются НИ в одной машине(id -4, Пикап)
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

-- Вывести двигатели, которые не используются ни в одной машине(id -4, Гибрид)
select  ce.id,
 ce.name engine_nameunuse
 from car_engines ce
 left join car c on ce.id = c.engine_id
 where c.id is null;

 ---- Вывести коробки передач, которые не используются ни в одной машине(id -4, Гибрит робот)
 select ct.id,
 ct.name as transmission_name_un_use
 from car_transmissions ct
 left join
 car c on ct.id = c.transmission_id
 where c.id is null;
