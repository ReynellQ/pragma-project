create sequence hibernate_sequence start 1 increment 1;
create table category (id int8 not null, description varchar(255), name varchar(255), primary key (id));
create table client_order (id int8 not null, date date, id_chef int8, id_client int8, id_restaurant int8, state int4, primary key (id));
create table food_plate (id int8 not null, active boolean default true, description varchar(255), id_category int8, id_restaurant int8, name varchar(255), price int8, url_image varchar(255), primary key (id));
create table order_foodplates (id_foodplate int8 not null, id_order int8 not null, quantity int8, primary key (id_foodplate, id_order));
create table order_ticket (id_order int8 not null, code varchar(255), primary key (id_order));
create table restaurant (id int8 not null, address varchar(255), id_owner int8, name varchar(255), nit int8, phone varchar(255), url_logo varchar(255), primary key (id));
create table restaurant_employee (id_restaurant int8 not null, id_user int8 not null, primary key (id_restaurant, id_user));
alter table if exists client_order add constraint FKhmn5rjwxjgn0xb2rk8xs9q3cx foreign key (id_restaurant, id_chef) references restaurant_employee;
alter table if exists food_plate add constraint FKgghsfm28nv9lhmg3otvmk4o9e foreign key (id_category) references category;
alter table if exists food_plate add constraint FK65t6dxfr90t0gamr7ecll7gb5 foreign key (id_restaurant) references restaurant;
alter table if exists order_foodplates add constraint FKohgu2cfqw72axdye2uimwbt4s foreign key (id_foodplate) references food_plate;
alter table if exists order_foodplates add constraint FK79xcid7g7r9spgr95nb109msx foreign key (id_order) references client_order;
alter table if exists restaurant_employee add constraint FK9jhdphxnqdiq982qgvfc6acg0 foreign key (id_restaurant) references restaurant;
create sequence hibernate_sequence start 1 increment 1;
create table category (id int8 not null, description varchar(255), name varchar(255), primary key (id));
create table client_order (id int8 not null, date date, id_chef int8, id_client int8, id_restaurant int8, state int4, primary key (id));
create table food_plate (id int8 not null, active boolean default true, description varchar(255), id_category int8, id_restaurant int8, name varchar(255), price int8, url_image varchar(255), primary key (id));
create table order_foodplates (id_foodplate int8 not null, id_order int8 not null, quantity int8, primary key (id_foodplate, id_order));
create table order_ticket (id_order int8 not null, code varchar(255), primary key (id_order));
create table restaurant (id int8 not null, address varchar(255), id_owner int8, name varchar(255), nit int8, phone varchar(255), url_logo varchar(255), primary key (id));
create table restaurant_employee (id_restaurant int8 not null, id_user int8 not null, primary key (id_restaurant, id_user));
alter table if exists client_order add constraint FKhmn5rjwxjgn0xb2rk8xs9q3cx foreign key (id_restaurant, id_chef) references restaurant_employee;
alter table if exists food_plate add constraint FKgghsfm28nv9lhmg3otvmk4o9e foreign key (id_category) references category;
alter table if exists food_plate add constraint FK65t6dxfr90t0gamr7ecll7gb5 foreign key (id_restaurant) references restaurant;
alter table if exists order_foodplates add constraint FKohgu2cfqw72axdye2uimwbt4s foreign key (id_foodplate) references food_plate;
alter table if exists order_foodplates add constraint FK79xcid7g7r9spgr95nb109msx foreign key (id_order) references client_order;
alter table if exists restaurant_employee add constraint FK9jhdphxnqdiq982qgvfc6acg0 foreign key (id_restaurant) references restaurant;
create sequence hibernate_sequence start 1 increment 1;
create table category (id int8 not null, description varchar(255), name varchar(255), primary key (id));
create table client_order (id int8 not null, date date, id_chef int8, id_client int8, id_restaurant int8, state int4, primary key (id));
create table food_plate (id int8 not null, active boolean default true, description varchar(255), id_category int8, id_restaurant int8, name varchar(255), price int8, url_image varchar(255), primary key (id));
create table order_foodplates (id_foodplate int8 not null, id_order int8 not null, quantity int8, primary key (id_foodplate, id_order));
create table order_ticket (id_order int8 not null, code varchar(255), primary key (id_order));
create table restaurant (id int8 not null, address varchar(255), id_owner int8, name varchar(255), nit int8, phone varchar(255), url_logo varchar(255), primary key (id));
create table restaurant_employee (id_restaurant int8 not null, id_user int8 not null, primary key (id_restaurant, id_user));
alter table if exists client_order add constraint FKhmn5rjwxjgn0xb2rk8xs9q3cx foreign key (id_restaurant, id_chef) references restaurant_employee;
alter table if exists food_plate add constraint FKgghsfm28nv9lhmg3otvmk4o9e foreign key (id_category) references category;
alter table if exists food_plate add constraint FK65t6dxfr90t0gamr7ecll7gb5 foreign key (id_restaurant) references restaurant;
alter table if exists order_foodplates add constraint FKohgu2cfqw72axdye2uimwbt4s foreign key (id_foodplate) references food_plate;
alter table if exists order_foodplates add constraint FK79xcid7g7r9spgr95nb109msx foreign key (id_order) references client_order;
alter table if exists restaurant_employee add constraint FK9jhdphxnqdiq982qgvfc6acg0 foreign key (id_restaurant) references restaurant;
create sequence hibernate_sequence start 1 increment 1;
create table category (id int8 not null, description varchar(255), name varchar(255), primary key (id));
create table client_order (id int8 not null, date date, id_chef int8, id_client int8, id_restaurant int8, state int4, primary key (id));
create table food_plate (id int8 not null, active boolean default true, description varchar(255), id_category int8, id_restaurant int8, name varchar(255), price int8, url_image varchar(255), primary key (id));
create table order_foodplates (id_foodplate int8 not null, id_order int8 not null, quantity int8, primary key (id_foodplate, id_order));
create table order_ticket (id_order int8 not null, code varchar(255), primary key (id_order));
create table restaurant (id int8 not null, address varchar(255), id_owner int8, name varchar(255), nit int8, phone varchar(255), url_logo varchar(255), primary key (id));
create table restaurant_employee (id_restaurant int8 not null, id_user int8 not null, primary key (id_restaurant, id_user));
alter table if exists client_order add constraint FKhmn5rjwxjgn0xb2rk8xs9q3cx foreign key (id_restaurant, id_chef) references restaurant_employee;
alter table if exists food_plate add constraint FKgghsfm28nv9lhmg3otvmk4o9e foreign key (id_category) references category;
alter table if exists food_plate add constraint FK65t6dxfr90t0gamr7ecll7gb5 foreign key (id_restaurant) references restaurant;
alter table if exists order_foodplates add constraint FKohgu2cfqw72axdye2uimwbt4s foreign key (id_foodplate) references food_plate;
alter table if exists order_foodplates add constraint FK79xcid7g7r9spgr95nb109msx foreign key (id_order) references client_order;
alter table if exists restaurant_employee add constraint FK9jhdphxnqdiq982qgvfc6acg0 foreign key (id_restaurant) references restaurant;
create sequence hibernate_sequence start 1 increment 1;
create table category (id int8 not null, description varchar(255), name varchar(255), primary key (id));
create table client_order (id int8 not null, date date, id_chef int8, id_client int8, id_restaurant int8, state int4, primary key (id));
create table food_plate (id int8 not null, active boolean default true, description varchar(255), id_category int8, id_restaurant int8, name varchar(255), price int8, url_image varchar(255), primary key (id));
create table order_foodplates (id_foodplate int8 not null, id_order int8 not null, quantity int8, primary key (id_foodplate, id_order));
create table order_ticket (id_order int8 not null, code varchar(255), primary key (id_order));
create table restaurant (id int8 not null, address varchar(255), id_owner int8, name varchar(255), nit int8, phone varchar(255), url_logo varchar(255), primary key (id));
create table restaurant_employee (id_restaurant int8 not null, id_user int8 not null, primary key (id_restaurant, id_user));
alter table if exists client_order add constraint FKhmn5rjwxjgn0xb2rk8xs9q3cx foreign key (id_restaurant, id_chef) references restaurant_employee;
alter table if exists food_plate add constraint FKgghsfm28nv9lhmg3otvmk4o9e foreign key (id_category) references category;
alter table if exists food_plate add constraint FK65t6dxfr90t0gamr7ecll7gb5 foreign key (id_restaurant) references restaurant;
alter table if exists order_foodplates add constraint FKohgu2cfqw72axdye2uimwbt4s foreign key (id_foodplate) references food_plate;
alter table if exists order_foodplates add constraint FK79xcid7g7r9spgr95nb109msx foreign key (id_order) references client_order;
alter table if exists restaurant_employee add constraint FK9jhdphxnqdiq982qgvfc6acg0 foreign key (id_restaurant) references restaurant;
create sequence hibernate_sequence start 1 increment 1;
create table category (id int8 not null, description varchar(255), name varchar(255), primary key (id));
create table client_order (id int8 not null, date date, id_chef int8, id_client int8, id_restaurant int8, state int4, primary key (id));
create table food_plate (id int8 not null, active boolean default true, description varchar(255), id_category int8, id_restaurant int8, name varchar(255), price int8, url_image varchar(255), primary key (id));
create table order_foodplates (id_foodplate int8 not null, id_order int8 not null, quantity int8, primary key (id_foodplate, id_order));
create table order_ticket (id_order int8 not null, code varchar(255), primary key (id_order));
create table restaurant (id int8 not null, address varchar(255), id_owner int8, name varchar(255), nit int8, phone varchar(255), url_logo varchar(255), primary key (id));
create table restaurant_employee (id_restaurant int8 not null, id_user int8 not null, primary key (id_restaurant, id_user));
alter table if exists client_order add constraint FKhmn5rjwxjgn0xb2rk8xs9q3cx foreign key (id_restaurant, id_chef) references restaurant_employee;
alter table if exists food_plate add constraint FKgghsfm28nv9lhmg3otvmk4o9e foreign key (id_category) references category;
alter table if exists food_plate add constraint FK65t6dxfr90t0gamr7ecll7gb5 foreign key (id_restaurant) references restaurant;
alter table if exists order_foodplates add constraint FKohgu2cfqw72axdye2uimwbt4s foreign key (id_foodplate) references food_plate;
alter table if exists order_foodplates add constraint FK79xcid7g7r9spgr95nb109msx foreign key (id_order) references client_order;
alter table if exists restaurant_employee add constraint FK9jhdphxnqdiq982qgvfc6acg0 foreign key (id_restaurant) references restaurant;
create sequence hibernate_sequence start 1 increment 1;
create table category (id int8 not null, description varchar(255), name varchar(255), primary key (id));
create table client_order (id int8 not null, date date, id_chef int8, id_client int8, id_restaurant int8, state int4, primary key (id));
create table food_plate (id int8 not null, active boolean default true, description varchar(255), id_category int8, id_restaurant int8, name varchar(255), price int8, url_image varchar(255), primary key (id));
create table order_foodplates (id_foodplate int8 not null, id_order int8 not null, quantity int8, primary key (id_foodplate, id_order));
create table order_ticket (id_order int8 not null, code varchar(255), primary key (id_order));
create table restaurant (id int8 not null, address varchar(255), id_owner int8, name varchar(255), nit int8, phone varchar(255), url_logo varchar(255), primary key (id));
create table restaurant_employee (id_restaurant int8 not null, id_user int8 not null, primary key (id_restaurant, id_user));
alter table if exists client_order add constraint FKhmn5rjwxjgn0xb2rk8xs9q3cx foreign key (id_restaurant, id_chef) references restaurant_employee;
alter table if exists food_plate add constraint FKgghsfm28nv9lhmg3otvmk4o9e foreign key (id_category) references category;
alter table if exists food_plate add constraint FK65t6dxfr90t0gamr7ecll7gb5 foreign key (id_restaurant) references restaurant;
alter table if exists order_foodplates add constraint FKohgu2cfqw72axdye2uimwbt4s foreign key (id_foodplate) references food_plate;
alter table if exists order_foodplates add constraint FK79xcid7g7r9spgr95nb109msx foreign key (id_order) references client_order;
alter table if exists restaurant_employee add constraint FK9jhdphxnqdiq982qgvfc6acg0 foreign key (id_restaurant) references restaurant;
create sequence hibernate_sequence start 1 increment 1;
create table category (id int8 not null, description varchar(255), name varchar(255), primary key (id));
create table client_order (id int8 not null, date date, id_chef int8, id_client int8, id_restaurant int8, state int4, primary key (id));
create table food_plate (id int8 not null, active boolean default true, description varchar(255), id_category int8, id_restaurant int8, name varchar(255), price int8, url_image varchar(255), primary key (id));
create table order_foodplates (id_foodplate int8 not null, id_order int8 not null, quantity int8, primary key (id_foodplate, id_order));
create table order_ticket (id_order int8 not null, code varchar(255), primary key (id_order));
create table restaurant (id int8 not null, address varchar(255), id_owner int8, name varchar(255), nit int8, phone varchar(255), url_logo varchar(255), primary key (id));
create table restaurant_employee (id_restaurant int8 not null, id_user int8 not null, primary key (id_restaurant, id_user));
alter table if exists client_order add constraint FKhmn5rjwxjgn0xb2rk8xs9q3cx foreign key (id_restaurant, id_chef) references restaurant_employee;
alter table if exists food_plate add constraint FKgghsfm28nv9lhmg3otvmk4o9e foreign key (id_category) references category;
alter table if exists food_plate add constraint FK65t6dxfr90t0gamr7ecll7gb5 foreign key (id_restaurant) references restaurant;
alter table if exists order_foodplates add constraint FKohgu2cfqw72axdye2uimwbt4s foreign key (id_foodplate) references food_plate;
alter table if exists order_foodplates add constraint FK79xcid7g7r9spgr95nb109msx foreign key (id_order) references client_order;
alter table if exists restaurant_employee add constraint FK9jhdphxnqdiq982qgvfc6acg0 foreign key (id_restaurant) references restaurant;
