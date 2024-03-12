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