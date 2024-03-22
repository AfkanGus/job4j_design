--0. Join [#6862].
create table departments (
    id int primary key,
    "name" text not null
);

create table employees (
    id int primary key,
    "name" text,
    department_id int references departments(id)
);

insert into departments values (1, 'Sales'),
                               (2, 'Marketing'),
                               (3, 'HR'),
                               (4, 'IT'),
                               (5, 'Production'),
                               (6, 'Inginer'),
                               (7,'Draver'),
                               (8,'Doctor');


insert into employees values (1, 'Ivan Ivanov', 1),
                             (2, 'Petr Petrov', 1),
                             (3, 'Olga Sergeeva', 2),
                             (4, 'Michael Shnurov', 3),
                             (5, 'Irina Trubkina', 4),
                             (6, 'Evgenii Shtukov', null);
--2. Выполнить запросы с left, right, full, cross соединениями
--получить все строки из двух таблиц
select e.name,d.name from employees e full join departments d on e.department_id=d.id;
--полить список сотрудникоов вместе с их отделами
select * from employees e left join departments d on e.department_id=d.id;
--получить список отделов с их сотрудниками
select * from departments d right join employees e on d.id=e.department_id;
--получить список всех возможных комбинаций сотрудников и отделов
select * from employees e cross join departments d;
--3. Используя left join найти департаменты, у которых нет работников.
select * from departments d left join  employees e on d.id=e.department_id where e.id is null;
--4. Используя left и right join написать запросы, которые давали бы одинаковый результат
--(порядок вывода колонок в эти запросах также должен быть идентичный).
select * from departments d left join employees e on d.id = e.department_id;
select * from employees e right join departments d on e.department_id = d.id;


--5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
create table teens(
id serial primary key ,
"name" varchar(255),
gender varchar(255)
);
insert into teens (name,gender) values
('Вася', 'М'),
('Петя', 'М'),
('Саня', 'М'),
('Катя', 'Ж'),
('Света', 'Ж'),
('Оля', 'Ж');
select (t1.name , t2.name) from teens t1 cross join teens t2 where t1.gender <> t2.gender and t1.gender = 'Ж';