drop table if exists Users cascade;
drop table if exists UserRole cascade;
drop table if exists Product cascade;
drop table if exists Invoice cascade;
drop table if exists ProductInvoice cascade;
drop type if exists productType cascade;
drop view if exists FROZEN cascade;
drop view if exists REFRIGERATED cascade;
drop view if exists GROCERIES cascade;


create type productType as enum(
'frozen', 'refrigerated', 'groceries');

create table if not exists Users (
		id serial primary key,
		name varchar(100) not null,
		address varchar(100) not null,
		username varchar(100) not null,
		password varchar(100) not null

);

create table if not exists Product(
		id serial primary key,
		name varchar(100) not null,
		brand varchar(100) not null,
		price int not null,
		organic boolean not null,
		product_type productType not null
);

create table if not exists Invoice (
		id serial primary key,
		address varchar(100) not null,
		totalPrice int,
		user_id int references Users (id) on delete cascade
);

create table if not exists ProductInvoice (
		product_id int references Product (id) on delete cascade,
		invoice_id int references Invoice (id) on delete cascade,
		amount int not null,
		totalCost int not null
);

create or replace view FROZEN as select P.* from Product as P
where P.product_type = 'frozen';

create or replace view REFRIGERATED as select P.* from Product as P
where P.product_type = 'refrigerated';

create or replace view GROCERIES as select P.* from Product as P
where P.product_type = 'groceries';

create or replace procedure insert_product(
	name varchar(100),
	brand varchar(100),
	price int,
	organic boolean,
	product_type varchar(100)
)
LANGUAGE plpgsql
AS $$
begin
	insert into Product(name, brand, price, organic, product_type)
	values (name, brand, price, organic, cast(product_type as productType));
end; $$;

create or replace procedure delete_product(
	productId int
)
LANGUAGE plpgsql
AS $$
begin
	delete from Product where id = productId;
end; $$;

create or replace function insert_invoice(address varchar(100), totalPrice int, user_id int)
returns int
LANGUAGE plpgsql
as
$$
declare
	declaredId integer;
begin
	with new_Invoice as(
		insert into Invoice(address, totalPrice, user_id)
	values (address, totalPrice, user_id) returning id)

	select id into declaredId from new_Invoice;

	return declaredId;
end;
$$;

create or replace procedure create_user(
	name varchar(100),
	address varchar(100),
	username varchar(100),
	password varchar(100)
)
LANGUAGE plpgsql
AS $$
begin
	insert into Users(name, address, username, password)
	values (name, address, username, password);
end; $$;

create or replace procedure delete_user(
	userId int
)
LANGUAGE plpgsql
AS $$
begin
	delete from Users where id = userId;
end; $$;

call create_user('Mads'::text, 'Trold'::text, 'user'::text, 'password'::text);

call insert_product('Banan', 'COOP', 1, 't', 'groceries');

call insert_product('Is', 'COOP', 10, 't', 'frozen');

call insert_product('Mælk', 'COOP', 2, 't', 'refrigerated');