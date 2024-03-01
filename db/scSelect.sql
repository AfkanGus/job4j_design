create table fauna(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);
insert into fauna (name, avg_age, discovery_date)
values ('Megaladon_fish', 10000, '01.03.2024'),
('kraken_fish', 12000, '01.02.2024'),
('black_fish', 15000, '01.01.2024'),
('java',20,'01.01.1990'),
('lochnes_fish',200,'01.01.1945');
delete from fauna;
select * from  fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select name from fauna where discovery_date < '01.01.1950';