--агрегатные функции count()
create table transactions(
    id int not null primary key,
    purpose text,
    client_id int,
    amount int,
    payment_date timestamp
);
insert into transactions values(1, 'Credit', 1, 132, '2022-06-01 12:00:00');
insert into transactions values(2, 'Current payment', 2, 94, '2022-06-01 12:00:00');
insert into transactions values(3, 'Service', 3, 194, '2022-06-01 12:00:00');
insert into transactions values(4, 'Vacation', 4, 254, '2022-06-01 12:00:00');
insert into transactions values(5, 'Credit', 1, 244, '2022-06-01 12:00:00');
insert into transactions values(6, 'Current payment', 2, 114, '2022-06-01 12:00:00');
insert into transactions values(7, 'Service', 3, 178, '2022-06-01 12:00:00');
insert into transactions values(8, 'Vacation', 4, 196, '2022-06-01 12:00:00');
insert into transactions values(9, 'Credit', 1, 198, '2022-06-01 12:00:00');
insert into transactions values(10, 'Current payment', 2, 78, '2022-06-01 12:00:00');
insert into transactions values(11, 'Service', 3, 156, '2022-06-01 12:00:00');
insert into transactions values(12, 'Vacation', 4, 152, '2022-06-01 12:00:00');
    delete from transactions;
    --Для представленной ниже схемы transactions напишите запрос, который вернет среднее значение и сумму по столбцу amount.
    --Данные должны быть сгруппированы по столбцу purpose, а также отсортированы по этому столбцу по возрастанию.
    --В результатах выборки должны быть только столбцы purpose, AVG и SUM.
select purpose, avg(amount), sum(amount) from transactions group by purpose order by purpose asc;
select max(amount) from transactions;
--найдет максимальное значение в столбце amount для каждой из групп. Данные должны быть сгруппированы по столбцу purpose.
select purpose, max(amount) from transactions group by purpose;
--найти минимальное значение в столбце amount.
select min(amount) from transactions;
--найдет минимальное значение в столбце amount для каждой группы данных - они должны быть сгруппированы по столбцу purpose.
select purpose, min(amount) from transactions group by purpose;
--WHERE--
select * from transactions where  purpose != 'Vacation' and amount < 150;

---WHERE--
create table account(
id int null primary key,
"name" text
);
insert into account values(1, 'Petr Arsntev'),(2,'Ivan Ivanov'),(3,'Oleg Olegovic');
--Выборка по where id. Нужно получить запись с ID = 1;
select *from  account where id = 1;
-----------------------
create table customers(
id int not null primary key,
first_name text,
last_name text,
acitve boolean,
email text
);
insert into customers values(1, 'Petr', 'Arsentev', true, 'parsentev@bk.ru');
insert into customers values(2, 'Andrey', 'Hincu', false, 'anincu@bk.ru');
insert into customers values(3, 'Rail', 'Shamsemuhametov', true, 'rsham@bk.ru');
insert into customers values(4, 'Elena', 'Kartashova', false, 'ekart@bk.ru');
insert into customers values(5, 'Lana', 'Sergeeva', true, 'lserg@bk.ru');
--SELECT имена_столбцов_через_запятую FROM название_таблицы WHERE условие ORDER BY условие_сортировки
--напишите запрос, который вернет только те записи у которых значение в столбце active равно true
select  * from customers where acitve = true;

-- напишите запрос, который выполнит выборку всех записей, которые удовлетворяют
 --условию - длина строки в столбце first_name должна быть строго больше 4. Для того чтобы получить
 --длину строки используйте функцию length(название_столбца)
select * from customers where  length(first_name) > 4;
--столбце id должно быть меньше или равно 3
select * from customers where  id <= 3;
--Важная особенность - WHERE оценивается после FROM, но перед SELECT и ORDER BY. Поэтому если используются псевдонимы в SELECT, то мы не можем использовать их в WHERE.
select * from customers where length(last_name) != 8;
--условиям - длина строки в столбце first_name должна быть больше или равна 5 И значение в столбце active равно false.
select * from customers where length(first_name) >= 5 and acitve = false;
select * from customers where id > 3 or acitve = false;

select * from customers where first_name in('Ann','Anne','Annie');

--JOIN--
--чтобы получить данные из нескольких таблиц, используется INNER JOIN.
create table colors(
id int primary key,
"name" text
);
create table actions(
number int primary key,
dascription text,
color_id int references colors(id)
);
insert into colors values(1,'red'),(2,'black');
insert into actions values(1,'draw red',1 ),(2,'use black hole', 2);
-- результатах выборки должны быть отражены все строки, среди столбцов отражены name и
  --description связь между таблицами отражается по столбцу id в colors и color_id в actions.
select name, dascription from colors inner join actions on colors.id = actions.color_id;
-------------------------------------------------------------------------------------------
create table cars (
    id int primary key,
    model text
);

create table engines (
    number int primary key,
    volume decimal,
    power int,
    car_id int references cars(id)
);

insert into cars values (1, 'Toyota Camry');
insert into cars values (2, 'Renault Sandero');
insert into cars values (3, 'Audi A6');
insert into cars values (4, 'Renault Sandero');
insert into engines values (1234, 2.5, 181, 1);
insert into engines values (5678, 1.2, 75, 2);
insert into engines values (1479, 1.6, 123, null);
insert into engines values (5072, 3.0, 231, null);
--RIGHT--
select id, model, number, volume, power from cars right join engines on cars.id=engines.car_id;
select * from cars c full join engines e on c.id=e.car_id;







-- выполнит выборку всех строк, в результатах должны быть отражены данные столбцов id,
 --model, volume, power, связь между таблицами осуществляется с помощью столбцов id  и car_id.
select id,model,volume,power from cars inner join engines on cars.id = engines.car_id;
--КАК РАБОТАЕТ INNER JOIN --
--Для каждой строки в таблице А внутреннее соединение сравнивает значение в столбце pka со значением в столбце fka каждой строки в таблице В:
-- если эти значения равны, то внутреннее соединение создает новую строку, которая содержит столбцы обеих таблиц и добавляет ее в результаты выборки;
--если же значения не равны, то внутреннее соединение просто игнорирует их и переходит к следующей строке
--Важно подчеркнуть, что согласно документации, если не указано иного по умолчанию выполняется INNER JOIN.
 --Т.е. слово INNER в запросе можно опустить, и мы получим аналогичный результат. Таким образом, представленный выше запрос будет иметь вид:
select c.id,c.model,e.volume,e.power from cars as c join engines e on c.id = e.car_id;






-------------------------------------------------------------------------------------------------------------------

--- мы столкнемся с тем, что будут столбцы в разных таблицах иметь одинаковые имена.
create table accounts (
    id int primary key,
    email text,-- Одинаковое поле
    password text
);

create table users (
    id int primary key,
    email text,-- Одинаковое поле
    "name" text,
    account_id int references accounts(id)
);
insert into accounts values (1, 'for_auth_1@mail.ru', '12345');
insert into accounts values (2, 'for_auth_1@bk.ru', '67890');

insert into users values (1, 'email_1@mail.ru', 'first_name', 1);
insert into users values (2, 'email_2@bk.ru', 'second_name', 2);
--можно указывать AS для присвоения псевдонима
--для идентификации столбцов указывать псевдоним необходимо только там, где имена столбцов совпадают.
select a.id,a.email,a.password, u.email,u.name from accounts as a inner join users u on a.id = u.account_id;

select a.id,a.email,a.password,u.id,u.email,u.name from accounts a right join users u on a.id=u.account_id;

----3. Фильтры [#1732].
create table type(
id int primary key,
"name" text
);

create table product(
id int primary key,
"name" text,
expired_date timestamp,
price int,
type_id int references type(id)
);

insert into type values (1,'Сыр'),(2,'Молоко'),(3,'Мороженое');
insert into product values (1,'Сыр плавленный','2024-01-15',150,1);
insert into product values (2,'Сыр моцарелла','2024-02-15',300,1);
insert into product values (3,'Сыр Российский','2024-03-16',100,1);
insert into product values (4,'Молоко Летний луг','2024-03-16',50,2);
insert into product values (5,'Молоко Коровье','2024-01-15',60,2);
insert into product values (6,'Мороженое обычное','2024-03-16',30,3);
insert into product values (7,'Мороженое ванильное','2024-03-16',30,3);
insert into product values (8,'Мороженое чешское','2024-03-16',30,3);
insert into product values (9,'Мороженое польское','2024-03-16',31,3);
insert into product values (10,'Мороженое красное','2024-03-16',30,3);
insert into product values (11,'Мороженое фаворит','2024-03-16',30,3);
insert into product values (12,'Мороженое карона','2024-03-16',30,3);
insert into product values (14,'Мороженое жара','2024-03-16',30,3);
insert into product values (15,'Мороженое новогоднее','2024-03-16',30,3);
insert into product values (16,'Мороженое стужа','2024-03-16',30,3);
insert into product values (17,'Мороженое караван','2024-03-16',30,3);
-- 1. Запрос на получение всех продуктов с типом 'Сыр'.
select * from product inner join type on product.type_id = type.id where type.name = 'Сыр';
-- 2. Запрос на получение всех продуктов c именем "мороженое".
select * from product where product.name like  '%Мороженое%';
-- 3. Запрос на вывод товаров с истекшим сроком годности.
select * from product where product.expired_date < current_date;
-- 4. Запрос который выводит самый дорогой продукт по типам.
select * from product where price =( select max(price) from product);
 -- 5.Вывод количество продуктов для каждого типа.
select type.name Имя_типа, count(product.name) Количество from product inner join type on product.type_id = type.id group by type.name;
-- 6. Получение всех продуктов с типом сыр и молоко
select * from product inner join type on product.type_id = type.id where type.name in('Сыр','Молоко');
-- 7. Запрос, на вывод типов продуктов, которых осталось меньше 10 штук.
select t.name as Тип_товара from product p join type t on p.type_id = t.id group by t.id, t.name having count(p.type_id) < 10;
--8. Вывести все продукты и их тип.
select  p.name Наименования_товара, p.price Цена_товара, p.expired_date Срок_годности_товара, t.name Тип_товара
 from product p join type t on p.type_id = t.id;

---6. INNER JOIN. Объединение трех и более таблиц.
create table users(
id int primary key,
email text,
"name" text
);
create table accounts(
id int primary key,
login text,
password text,
user_id int references users(id)
);
insert into users values (1, 'email_1@mail.ru', 'first_name');
insert into users values (2, 'email_2@bk.ru', 'second_name');

insert into accounts values (1, 'login_1', '12345', 1);
insert into accounts values (2, 'login_2', '67890', 2);
-- SELECT запроса с использование INNER JOIN
select u.id,u.email,u.name, a.id,a.login,a.password from users u  join accounts a on u.id = a.user_id;

create table payment(
id int primary key,
amount decimal,
payment_data date,
account_id int references  accounts(id)
);
insert into payment values (1, 100.0, '2022-09-09', 1);
insert into payment values (2, 200.0, '2022-08-08', 2);
-- чтобы соединить 3 таблицы, необходимо поместить второе предложение INNER JOIN после первого предложения INNER JOIN
-- запрос объединения трех таблиц. В результатах выборки должны быть отражены значения столбцов id из таблицы users,
--email, name, login, password, amount, payment_date. Объединять будем по столбцам id из таблицы users и user_id,
--id из таблицы accounts и account_id. В качестве псевдонимов используйте первые буквы названия таблиц.
select u.id,u.email,u.name,a.login,a.password, p.amount, p.payment_data
from users u
join accounts a on u.id = a.user_id
join payment p on a.id = p.account_id;

--7. LEFT JOIN 1.
create table colors (
    id int primary key,
    "name" text
);

create table actions (
    number int primary key,
    description text,
    color_id int references colors(id)
);
insert into colors values(1, 'red');
insert into colors values(2, 'white');
insert into colors values(3, 'black');
insert into colors values(4, 'purple');

insert into actions values (1, 'draw red', 1);
insert into actions values (2, 'use black hole', 2);
--чтобы сделать выборку данных из таблицы А, которые могут иметь или не иметь соответствующие строки в
--таблице В, то необходимо использовать предложение LEFT JOIN.
--В выборке должны быть отражены значения столбцов id, name, number, description.
--Объединение будет производиться по столбцам id и color_id.
select id,name,number,description from colors left join actions on id=color_id;
select * from colors c full join actions a on c.id=a.color_id;

---RIGHT JOIN--
--Каким же образом это работает?
-- предложение RIGHT JOIN начинает выборку данных из правой таблицы – actions;
-- для каждой строки из правой таблицы (actions) RIGHT JOIN проверяет, равно ли значение color_id столбце таблицы actions значению в столбце id каждой строки из левой таблицы (colors);
-- если указанные значения равны, то RIGHT JOIN создает новую строку, которая содержит значения столбцов из обеих таблиц, которые указаны в SELECT и включает эту строку в результаты выборки;
-- если же указанные значения не равны, то RIGHT JOIN все равно создает новую строку, которая содержит столбцы из обеих таблиц и эта новая
--строка включается в результаты выборки. Однако в таком случае столбцы из левой таблицы colors значением NULL.
-- таким образом, RIGHT JOIN выбирает все строки из правой таблицы независимо от того, есть ли у них совпадающие строки из левой таблицы.
select c.id,c.name,a.number,a.description from colors c right join actions a on c.id=a.color_id;




create table accounts (
    id int primary key,
    email text,
    password text
);

create table users (
    id int primary key,
    email text,
    "name" text,
    account_id int references accounts(id)
);
insert into accounts values (1, 'some_for_auth_1@mail.ru', '1_3_5');
insert into accounts values (2, 'some_for_auth_1@bk.ru', '6%8%0');
insert into accounts values (3, 'for_auth_1@mail.ru', '12345');
insert into accounts values (4, 'for_auth_1@bk.ru', '67890');

insert into users values (1, 'email_1@mail.ru', 'first_name', 3);
insert into users values (2, 'email_2@bk.ru', 'second_name', 4);
--напишите запрос с использованием LEFT JOIN. Левая таблица – таблица accounts. Объединение осуществляется по
--столбцам id из таблицы accounts и account_id. В результатах выборки должны быть отражены значения столбцов
--id из таблицы accounts, email из таблицы accounts, password, id из таблицы users, email из таблицы users, name.
--В качестве псевдонимов используйте первую букву названия таблиц.
select a.id, a.email, a.password, u.id, u.email,u.name from accounts a left join users u on a.id = u.account_id;

--1. GROUP BY 1
-- вычислит средний балл студентов по каждому предмету. Таблица "Оценки" содержит поля: студент, предмет, оценка. Группировка будет по subject.
create table students(
id int,
subject varchar(50),
grade int
);
insert into students values(1,'Math',85),   (1, 'Physics', 90),
       (2, 'Math', 78),
       (2, 'Physics', 88),
       (3, 'Math', 92),
       (3, 'Physics', 95);
  select subject, avg(grade) from students group by subject;
--Необходимо учесть - любой столбец, который указан в SELECT (столбец, который хранит результат
--вычисления агрегатных функций, не считается), должен быть указан после GROUP BY.
select id,sum(grade) from students group by id;


--WHERE--
create table subjects(
id int not null primary key,
"name" text,
grade int,
start_data timestamp
);
insert into subjects values(1, 'Math', 50, current_date);
insert into subjects values(2, 'English', 75, current_date);
insert into subjects values(3, 'Sociology', 65, current_date);
insert into subjects values(4, 'Economics', 60, current_date);
insert into subjects values(5, 'Computer Science', 70, current_date);
select * from subjects where grade in (50,60,70);



--FULL OUTER JOIN--

create table departments (
    id int primary key,
    "name" text not null
);
delete from departments;
create table employees (
    id int primary key,
    "name" text,
    department_id int references departments(id)
);
delete from employees;
--Для представленной ниже схемы напишите запрос с использованием FULL OUTER JOIN. Результирующий
 --набор должен включать всех сотрудников, принадлежащих отделу, и всех отделов, в которых есть сотрудник.
--Кроме того, результат должен включать каждого сотрудника, не принадлежащего отделу, и каждый отдел, в котором нет
 --сотрудников. В результат отражаем значения столбцов name из каждой таблицы. Левая таблица – employees, правая – departments.
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
select (t1.name , t2.name) from teens t1 cross join teens t2 where t1.gender != t2.gender and t1.gender = 'М';










create table orders
(
    id           int primary key,
    number_month int,
    amount       int
);
insert into orders
values (1, 1, 100),
       (2, 2, 150),
       (3, 1, 200),
       (4, 2, 50),
       (5, 3, 120);
select number_month, count(amount) from orders group by number_month;

-- запрос, который определит количество проданных товаров для каждого продавца.
--Используйте таблицу "Продажи" с полями: продавец, товар, количество. Группировка будет по seller_id.
create table sales(
seller_id int,
product_id int,
quantity int
);
insert into sales values (1, 1, 10),
       (2, 2, 5),
       (1, 3, 8),
       (3, 4, 12),
       (2, 1, 15);
select seller_id, sum(quantity) from sales group by seller_id;

-- вычислит общий доход от продаж для каждой категории товаров. Используйте таблицы:
-- "Продукты" с полями: товар, категория, цена. - "Продажи" с полями: продажа, id товара, количество. Группировка будет по category.
create table products
(
    id int primary key,
    category   varchar(50),
    price      int
);

create table sales
(
    sale_id    int primary key,
    product_id int references products(id),
    quantity   int
);
insert into products
values (1, 'Electronics', 500),
       (2, 'Clothing', 30),
       (3, 'Electronics', 700),
       (4, 'Books', 20);

insert into sales
values (1, 1, 10),
       (2, 2, 5),
       (3, 3, 8),
       (4, 4, 12);
select p.category, sum(p.price * s.quantity) from products p join sales s on p.id = s.product_id group by p.category;

--0. Join [#6862]--
--«Внешней» здесь будет таблица, к которой присоединяют.
-- берется исходная таблица и для каждой ее записи находится запись, которая бы
 --удовлетворяла условию. Если она не будет найдена, то по столбцам будут стоять null
create table owners
(
    id   serial primary key,
    "name" varchar(255)
);

create table devices
(
    id       serial primary key,
    "name"     varchar(255),
    owner_id int references owners (id)
);

insert into owners(name)
values ('Owner 1');
insert into owners(name)
values ('Owner 2');
insert into owners(name)
values ('Owner 3');

insert into devices(name, owner_id)
values ('Device 1', 1);
insert into devices(name, owner_id)
values ('Device 2', 2);
insert into devices(name, owner_id)
values ('Device 3', 3);
insert into devices(name, owner_id)
values ('Device 4', null);
insert into devices(name, owner_id)
values ('Device 5', null);
insert into devices(name, owner_id)
values ('Device 6', 1);
--В данном случае «внешней» будет таблица табл1. Следовательно, выбираются записи из табл1 и
 --для них подбираются записи в таблице табл2. Очевидно, что в результате мы получим n записей,
 -- где n это число записей в таблице табл1, т.е. получим записи из табл1, только с присоединенными записями из табл2.
select * from devices d
left join owners o on d.owner_id=o.id;

select * from devices d
left join owners o on d.owner_id=o.id where  o.id is null;
--right outer join
--select .. from левая <тип> join правая on <условие>
select * from owners o
right join devices d
on d.owner_id=o.id;

select * from devices d
         right join owners o on d.owner_id = o.id;


select * from owners o
left join devices d
on o.id = d.owner_id;

select * from devices d
         full join owners o on d.owner_id = o.id;


select * from devices d
         left join owners o on d.owner_id = o.id
union
select * from devices d
         right join owners o on d.owner_id = o.id;


--cross join
--каждая строка из первой таблицы объединяется с каждой строкой из второй таблицы
select  * from devices d cross join owners o;
create table numbers
(
    num int unique
);

insert into numbers(num)
values (1);
insert into numbers(num)
values (2);
insert into numbers(num)
values (3);
--Таким образом, данный вид запроса может служить для генерации данных на уровне БД.
select n1.num as a, n2.num as b, (n1.num * n2.num) as "a*b=" from numbers n1
         cross join numbers n2;


--11. GROUP BY и WHERE 1.
create table grades(
id serial primary key,
"name" varchar(50),
subject varchar(50),
grade int
);
insert into grades(name,subject,grade) values
('Alice', 'Math', 85),
       ('Jack', 'Math', 70),
       ('Bob', 'Math', 78),
       ('Alice', 'Physics', 90),
       ('Bob', 'Physics', 88),
       ('Charlie', 'Math', 92),
       ('Charlie', 'Physics', 95),
       ('Jack', 'Physics', 84);
--Напишите запрос, который выведет топ-3 студентов по среднему баллу, учитывая только тех,
--кто получил оценки выше 80. Группировка будет по name.
select name,
avg(grade) as average
from grades
where grade>80
group by name
order by average desc
limit 3;

create table sales
(
    id           serial primary key,
    category     varchar(50),
    month_number int,
    amount       int
);

insert into sales (category, month_number, amount)
values ('Electronics', 2, 1000),
       ('Clothing', 2, 500),
       ('Electronics', 1, 800),
       ('Books', 3, 300),
       ('Electronics', 1, 1200);

select * from sales;
--Создайте запрос, который подсчитает суммарные продажи по категориям
--товаров за месяц с номером 2. Группировка будет по category.
select category,
sum(amount) as sum
from sales
where month_number = 2
group by category;




--Напишите запрос, который вычислит средний возраст сотрудников для каждого отдела,
--оставив только тех, кто младше 30 лет. Группировка будет по department.
create table employees
(
    id         serial primary key,
    "name"       varchar(50),
    department varchar(50),
    age        int
);
insert into employees (name, department, age)
values ('John', 'HR', 28),
       ('Jane', 'IT', 32),
       ('Bob', 'Finance', 29),
       ('Alice', 'IT', 26),
       ('Charlie', 'HR', 30);

select department,
avg(age) as avg
from employees
where age < 30
group by department;


create table orders
(
    id         serial primary key,
    client_id  int,
    order_year int,
    amount     int
);
insert into orders (client_id, order_year, amount)
values (1, 2023, 100),
       (2, 2023, 150),
       (1, 2023, 200),
       (3, 2023, 50),
       (2, 2023, 120);

--Создайте запрос, который подсчитает общее количество заказов для каждого клиента
--за текущий год. Группировка будет по client_id.
select  client_id,
count(amount) as count
from orders
where order_year  = 2023
group by client_id;


--Разработайте запрос, который найдет количество оценок и максимальную оценку по каждому предмету,
--учитывая только те которые больше 85. Группировка будет по subject.
create table scores
(
    student_id   serial primary key,
    student_name varchar(50),
    subject      varchar(50),
    score        int
);
insert into scores (student_name, subject, score)
values ('Alice', 'Math', 85),
       ('Bob', 'Math', 78),
       ('Alice', 'Physics', 90),
       ('Bob', 'Physics', 88),
       ('Charlie', 'Math', 92),
       ('Charlie', 'Physics', 95);

select subject,
count(score), max(score) as max_grade
from scores
where score > 85
group by subject;



--Предложение WHERE с оператором LIKE 3
create table airplanes(
id int not null primary key,
model text,
range int,
capacity int
);
insert into airplanes values(1, 'Airbus-320-200', 5700, 180);
insert into airplanes values(2, 'Airbus-321-200', 5600, 220);
insert into airplanes values(3, 'Airbus-319-100', 6700, 150);
insert into airplanes values(4, 'Cessna 208 Caravan', 1200, 13);
insert into airplanes values(5, 'Boeing 777-300', 11100, 450);
insert into airplanes values(6, 'Boeing 767-300', 7900, 350);
insert into airplanes values(7, 'Boeing 737-300', 4200, 145);
insert into airplanes values(8, 'Sukhoi SuperJet-100', 3000, 98);
insert into airplanes values(9, 'Bombardier CRJ-200', 2700, 50);

--который вернет все строки у которой в столбце model будет в конце строки последовательность символов 200.
select *
from airplanes
where model
like '%200';

--трока которая начинается с Airbus.
select id,model,range,capacity
from airplanes
where model
like 'Airbus%';

--ернет строки, в которых в столбце model будет строка которая начинается гарантировано
--из трех любых символов, дальше идет буква 'b' и далее - любой набор символов.
--символ "_" - он соответствует в точности одному любому символу.
--Т.е. если разбирать этот шаблон, то искомая строка должна начинаться с 2 символов,
--после которых следует искомая подстрока, а дальше - любой набор символов.
select *
from airplanes
where model
like '%__b%';
--в столбце model не содержится подстрока Airbus и Boeing.
select *
from airplanes
where model
not like 'Airbus%'
and model
not like 'Boeing%';

------------------------------------------
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
                               (5, 'Production');

insert into employees values (1, 'Ivan Ivanov', 1),
                             (2, 'Petr Petrov', 1),
                             (3, 'Olga Sergeeva', 2),
                             (4, 'Michael Shnurov', 3),
                             (5, 'Irina Trubkina', 4),
                             (6, 'Evgenii Shtukov', null);


--ель запроса – найти отдел, в котором нет сотрудников.
--В результирующем наборе должны быть отражены только значения столбцов name
--из обоих таблиц. Левая таблица – employees, правая – departments. Левая таблица – employees, правая – departments.
select e.name, d.name from departments d left join  employees e on d.id=e.department_id
where e.department_id is null;
---айти сотрудника, который не принадлежит ни к какому отделу. Левая таблица – employees, правая – departments.
select e.name, d.name from departments d right join employees e on d.id=e.department_id
where d.id is null;
--NATURAL JOIN – это соединение, которое создает неявное соединение на основе тех столбцов в
--соединяемых таблицах которые имеют одинаковые имена.
select * from departments natural join employees;

--------------------------------------------------------------
create table cars (
    car_id int primary key,
    model text
);

create table engines (
    number int primary key,
    volume decimal,
    power int,
    car_id int references cars(car_id)
);
insert into cars values (1, 'Toyota Camry');
insert into cars values (2, 'Kia Rio');
insert into cars values (3, 'Audi A6');
insert into cars values (4, 'Renault Sandero');

insert into engines values (1234, 2.5, 181, 1);
insert into engines values (1479, 1.6, 123, null);
insert into engines values (5678, 1.2, 75, 4);
insert into engines values (5072, 3.0, 231, null);
--Для представленной ниже схемы выполните запрос с использованием NATURAL JOIN.
--Это должен быть LEFT JOIN. В результатах отражаем значения столбцов model, volume, power.
select c.model, e.volume,e.power from cars c left join  engines e on c.car_id=e.car_id;

select c.model, e.volume, e.power
from cars c
left join engines e on c.car_id = e.car_id;
--Для представленной ниже схемы выполните запрос с использованием NATURAL JOIN.
--Это должен быть RIGHT JOIN. В результатах отражаем значения всех столбцов – используйте
--оператор звездочки (*).
select c.car_id, c.model, e.number,e.volume,e.power
 from cars c right join engines e on c.car_id=e.car_id;

-------21. NATURAL JOIN 4
--NATURAL JOIN – созд.неявное соединение на основе в таблицах с однаковыми именами.
--не нужно указывать логическое выражения для соединения,т.к.исползуется неявное предложение.
--основаное на совпадении сталбцов в таб.
create table departments (
    department_id int primary key,
    "name" text not null
);
create table employees (
    id int primary key,
    employee_name text,
    department_id int references departments(department_id)
);
INSERT INTO departments VALUES (1, 'Sales'),
                               (2, 'Marketing'),
                               (3, 'HR'),
                               (4, 'IT'),
                               (5, 'Production');

INSERT INTO employees VALUES (1, 'Ivan Ivanov', 1),
                             (2, 'Petr Petrov', 1),
                             (3, 'Olga Sergeeva', 2),
                             (4, 'Michael Shnurov', 3),
                             (5, 'Irina Trubkina', 4),
                             (6, 'Evgenii Shtukov', null);
--результатах отражены значения всех столбцов, используйте (*).
select * from departments natural join employees;

--22. NATURAL JOIN 5.
--Тем не менее, следует избегать использования NATURAL JOIN в том случае, если это возможно,
 --поскольку иногда это может привести к неожиданному результату
 CREATE TABLE departments (
    department_id int primary key,
    "name" text not null
);

CREATE TABLE employees (
    id int primary key,
    "name" text,
    department_id int references departments(department_id)
);
select * from departments natural join employees;
--Вернет пустой набор результатов. Связано это с тем, что в
 --таблицах имеется одинаковый столбец name, который и будет использован для выполнения NATURAL JOIN.
































