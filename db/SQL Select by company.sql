--3. SQL Select by company [#2760]
create table company
(
    id integer not null,
    "name" character varying,
    constraint company_pkey primary key (id)
);

create table person
(
    id integer not null,
    "name" character varying,
    company_id integer references company(id),
    constraint person_pkey primary key (id)
);
insert into company (id, name) values
(1, 'Tech Corp'),
(2, 'Finance Group'),
(3, 'Health Inc'),
(4, 'EduTech'),
(5, 'Logistics LLC');

insert into person (id, name, company_id) values
(1, 'Alice', 1),
(2, 'Bob', 2),
(3, 'Charlie', 1),
(4, 'David', 3),
(5, 'Eve', 5),
(6, 'Frank', 4),
(7, 'Grace', 4),
(8, 'Heidi', 2),
(9, 'Ivan', 5),
(10, 'Judy', 1);
(11, 'Kim', 4),
(12, 'Leo', 2);
--названия компаний и имена кто не состоит в компании с id = 5
select p.name as person_name, c.name as company_name
from person p
 inner join company c on p.company_id = c.id
where p.company_id != 5;


select c.name as company_name, count(p.id) as person_count
from person p
inner join company c on p.company_id = c.id
group by c.id, c.name
having count(p.id) = (
    select max(person_count)
    from (
        select count(p.id) as person_count
        from person p
        group by p.company_id
    ) as count
);
