--3. Хранимые процедуры и функции [#504804].
-- Добавьте процедуру и функцию, которая будет удалять записи. Условия выбирайте сами – удаление по id,
--удалить если количество товара равно 0 и т.п.
create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 3, 100);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 3, 150);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 3, 200);

select * from products;

create or replace procedure delete_data(d_price integer)
language 'plpgsql'
as $$
    BEGIN
    delete from products
	where price > d_price;
    END
$$;

call delete_data(150);

create or replace function del_data(d_price integer)
returns void
language 'plpgsql'
as $$
BEGIN
		DELETE FROM products
		WHERE price > d_price;
	END;
$$;

select del_data(50)