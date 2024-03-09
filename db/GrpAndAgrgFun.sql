create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into people (name) values ('Ivan'),('Stepan'),('Oleg');

insert into devices (name,price) values ('Smartphone',5100),('Notebook', 4000),('Ipad', 6000);

insert into devices_people (people_id, device_id) values (1,1),(1,2),(2,1),(3,1),(3,2),(3,3);

--Агрегатные функции
select avg(price) from devices;
select min(price) from devices;
select max(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name;

--Группировка
select p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name;

--Фильтрование с агрегатными функциями
select p.name, round(avg(d.price))
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
having round(avg(d.price)) > 5000;