-- drop database if exists tacocloud;
-- create database tacocloud;
use tacocloud;

create table if not exists users (
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    fullName varchar(100),
    street varchar(100),
    city varchar(60),
    zipCode varchar(20),
    phoneNumber varchar(20),
    accountValid integer NOT NULL,
    credentialsValid integer NOT NULL,
    active integer NOT NULL,
    enabled integer NOT NULL,

    primary key (username)
);

create table if not exists authorities (
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,

    foreign key (username) references users(username)
);

create table if not exists Ingredient (
	id varchar(4) not null primary key unique,
	name varchar(25) not null,
	type varchar(10) not null
);

create table if not exists Taco (
	id bigint not null primary key auto_increment,
	tacoName varchar(50) not null,
	createdAt timestamp not null
);


 create table if not exists Taco_Ingredients (
 	taco_id bigint not null,
 	ingredients_id varchar(4) not null
 );

 alter table Taco_Ingredients add foreign key (taco_id) references Taco(id);
 alter table Taco_Ingredients add foreign key (ingredients_id) references Ingredient(id);

create table if not exists Taco_Order (
	id bigint not null primary key auto_increment,
	name varchar(50) not null,
	street varchar(50) not null,
	city varchar(50) not null,
	zip varchar(10) not null,
	ccNumber varchar(16) not null,
	expiration varchar(5) not null,
	cvv varchar(3) not null,
	placedAt timestamp not null,
    modifiedBy varchar(50),

    foreign key (modifiedBy) references users(username)
);


 -- the same situation like for Taco_Ingredients
 create table if not exists Taco_Order_Tacos (
 	order_id bigint not null,
 	tacos_id bigint not null
 );

 alter table Taco_Order_Tacos add foreign key (tacos_id) references Taco_Order(id);
 alter table Taco_Order_Tacos add foreign key (order_id) references Taco(id);

