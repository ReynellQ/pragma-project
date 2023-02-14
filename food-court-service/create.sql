
create sequence hibernate_sequence start 1 increment 1;
create table categoria (id int8 not null, descripcion varchar(255), nombre varchar(255), primary key (id));
create table platos (id int8 not null, activo boolean default true, descripcion varchar(255), id_categoria int8, id_restaurante int8, nombre varchar(255), precio int8, url_imagen varchar(255), primary key (id));
create table restaurantes (id int8 not null, direccion varchar(255), id_propietario int8, nit int8, nombre varchar(255), telefono varchar(255), url_logo varchar(255), primary key (id));
alter table if exists platos add constraint FKgxx76oqdq4ausvp6hspn57shv foreign key (id_categoria) references categoria;
alter table if exists platos add constraint FKdm75cs23t4omy649bpy3a052c foreign key (id_restaurante) references restaurantes;
