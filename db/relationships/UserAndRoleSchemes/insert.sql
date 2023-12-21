
insert into role (name_role) values
    ('покупатель'),
    ('производитель'),
    ('продавец');

insert into users(name_users, role_id)
values ('ivan', 1), ('ignat', 2), ('irina', 3);

insert into rules(name_rules)
values ('ищет товар'),
('производит товар'),
('продает товар');


insert into role_rules(role_id, rule_id)
values
(1, 3), (1, 2);


insert into category(name_category)
values
('покупка товара'),
('отмена товара'),
('доставка');


insert into state(name_state)
values
('заявка на покупку'),
('заявка на возврат или отмену'),
('заявка на продажу и отправку');

insert into item(user_id,category_id,state_id)values
(1,1,1),(2,1,3),(3,2,1);

insert into comments(ask_comment,item_id)
values ('Задать вопрос',1),
('написать на почту',2);

insert into attachs(name_attachs,item_id)values
('file1.txt',1),
('file2.txt',3),
('file3.txt',2);
