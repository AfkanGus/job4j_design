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