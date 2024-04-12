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
