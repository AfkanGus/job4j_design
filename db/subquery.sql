--9. Подзапросы [#504874].
CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);
insert into customers
values (1,'ivan','petrov',10,'sudan'),
(2,'andrey','ivanov',15,'uk'),
(3,'slava','black',20,'armenia');
select * from customers;
--список клиентов, возраст которых является минимальным.
select * from customers
 where age = (select min(age) from customers);
CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);
insert into orders (amount, customer_id)
values
(100, 1),
(200, 2);
-- список клиентов, которые еще не сделали ни одного заказа.
select * from customers
where id not in(select customer_id from orders);