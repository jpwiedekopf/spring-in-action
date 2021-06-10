drop table if exists taco_ingredients cascade;
drop table if exists ingredient cascade;
drop table if exists taco_order_tacos cascade;
drop table if exists taco cascade;
drop table if exists taco_order cascade;
drop table if exists user cascade;

create table if not exists user
(
    id           bigint primary key auto_increment,
    username     varchar(50)  not null,
    password     varchar(100) not null,
    full_name     varchar(50)  not null,
    street       varchar(50)  not null,
    city         varchar(50)  not null,
    state        varchar(50)  not null,
    zip          varchar(10)  not null,
    phone_number varchar(50)  not null
);

create table if not exists ingredient
(
    db_id bigint primary key auto_increment,
    id    varchar(4)  not null,
    name  varchar(25) not null,
    type  varchar(10) not null
);

create table if not exists taco
(
    id         bigint primary key auto_increment,
    name       varchar(50) not null,
    created_at timestamp   not null
);

create table if not exists taco_ingredients
(
    taco_id           bigint not null,
    ingredients_db_id bigint not null
);

alter table taco_ingredients
    add foreign key (taco_id) references taco (id);

alter table taco_ingredients
    add foreign key (ingredients_db_id) references ingredient (db_id);

create table if not exists taco_order
(
    id              bigint primary key auto_increment,
    delivery_name   varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city   varchar(50) not null,
    delivery_state  varchar(50) not null,
    delivery_zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    placed_at       timestamp   not null,
    user_id            bigint      not null
);

alter table taco_order
    add foreign key (user_id) references user (id);

create table if not exists taco_order_tacos
(
    taco_order bigint not null,
    taco       bigint not null
);

alter table taco_order_tacos
    add foreign key (taco_order) references taco_order (id);
alter table taco_order_tacos
    add foreign key (taco) references taco (id);