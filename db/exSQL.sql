create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);
-- Триггер должен срабатывать после вставки данных, для любого товара и просто
 --насчитывать налог на товар (нужно прибавить налог к цене товара).
  --Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create
or replace function tax_statement_level()
    returns trigger as
$$
begin
    update products
    set price = price + price * 0.2;
    return new;
end;
$$
language plpgsql;

create trigger tax_trigger_statement_level
    after insert
    on products
    referencing new table as
    inserted
    for each statement
    execute procedure tax_statement_level();
 -- Делаем вставку в таблицу продуктов
insert into products (name, producer, count, price)
values
('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price)
values
('product_2', 'producer_2', 3, 100);
select * from products;

-- Триггер должен срабатывать до вставки данных и насчитывать налог на товар
--(нужно прибавить налог к цене товара). Здесь используем row уровень.
create or replace function tax_row_level()
    returns trigger as
$$
begin
    new.price = new.price + new.price * 0.2;
    return new;
end;
$$
language plpgsql;

create trigger tax_trigger_row_level
    before insert
    on products
    for each row
    execute procedure tax_row_level();


insert into products (name, producer, count, price)
values
('product_3', 'producer_3', 3, 10),
('product_4', 'producer_4', 3, 50),
('product_5', 'producer_5', 3, 100);
select * from products;

-- написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
--будет заносить имя, цену и текущую дату в таблицу history_of_price.
create table history_of_price
(
    id    serial primary key,
    "name"  varchar(50),
    price integer,
    date  timestamp
);
create
 or replace function log_price_history()
    returns trigger as
$$
begin
    insert into history_of_price (name, price, date)
    values (new.name, new.price, now()); -- Запись в историю цен
    return new;
end;
$$
language plpgsql;

create trigger price_history_trigger
    after insert
    on products
    for each row
    execute procedure log_price_history();

insert into products (name, producer, count, price)
values
('product_6', 'producer_6', 3, 120),
('product_7', 'producer_7', 3, 220),
('product_8', 'producer_8', 3, 330);

select * from products;

select * from history_of_price;








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
-- добавим хранимую процедуру, которая позволит вставлять данные в эту таблицу.
create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;
--используют CALL, т.е. вызывать процедуру
call insert_data('product_2', 'producer_2', 15, 32);
--добавим процедуру для обновления данных в таблице.
create
or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
        end if;
        if
            tax > 0 THEN
            update products
            set price = price + price * tax;
        end if;
    END;
$$;
-- вызова нашей процедуры (в параметрах указываем число, на которое уменьшим count - 10 и id записи - 1).
call update_data(10, 0, 1);
-- Добавим еще несколько записей в таблицу:
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);
-- увеличим все цены на сумму налога
call update_data(0, 0.2, 0);


--Обновить процедуру (например, переименовать) как и обычно можно с помощью ALTER PROCEDURE.
alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;
--Удалить процедуру можно с помощью DROP.
drop procedure update_data(u_count integer, tax float, u_id integer);

---------Зачистим таблицу products перед использованием хранимых функций:
delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;
--как создавать и использовать хранимые функции
create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void --обратите внимание, что добавляется блок return.
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;
--Функции, в отличие от процедур, вызываются через обычный SELECT
select f_insert_data('product_1', 'producer_1', 25, 50);
----добавим функцию для обновления данных в таблице.
create
or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
            select into result count
            from products
            where id = u_id;
        end if;
        if tax > 0 THEN
            update products
            set price = price + price * tax;
            select into result sum(price)--При обновлении цен мы возвращаем общую сумму всех цен товаров.
            from products;
        end if;
        return result;--в качестве возвращаемого значения при обновлении количества товара мы вернем обновленное количество товара.
    end;
$$;
select f_update_data(10, 0, 1);
--Добавим в таблицу еще несколько записей:
select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);
select f_update_data(0, 0.2, 0);
