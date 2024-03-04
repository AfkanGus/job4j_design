create table users(
id serial primary key,
	name varchar(255)
);
create table posts(
    id serial primary key,
	name varchar(255),
	content text,
	users_id int references users(id)
);

-- Вставляем данные в таблицу "users"
insert into users (name) values ('Иван');
insert into users (name) values ('Вася');
insert into users (name) values ('Коля');

-- Вставляем данные в таблицу "posts"
insert into posts (name, content, users_id) values ('Пост 1', 'Содержание поста 1', 1);
insert into posts (name, content, users_id) values ('Пост 2', 'Содержание поста 2', 1);
insert into posts (name, content, users_id) values ('Пост 3', 'Содержание поста 3', 2);
insert into posts (name, content, users_id) values ('Пост 4', 'Содержание поста 4', 3);
insert into posts (name, content, users_id) values ('Пост 5', 'Содержание поста 5', 3);

select u.name, p.name,p.content
from users as u join posts as p on p.users_id = u.id

select u.name as Название_пользователя, p.name as Название_поста, p.content as Содержание_поста
from users as u join posts as p on p.users_id = u.id;

select u.name as "Название пользователя", p.name as "Название поста", p.content as "Содержание поста"
from users as u join posts as p on p.users_id = u.id;

