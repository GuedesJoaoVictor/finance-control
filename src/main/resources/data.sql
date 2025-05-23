create table users(cpf varchar(14) not null primary key, name varchar(255) not null, email varchar(155) not null, password varchar(255) not null);

create table bank(id serial not null primary key, name varchar(100) not null, type varchar(100));

create table user_bank(id serial not null primary key, name varchar(100) not null, user_cpf varchar(14) not null references users(cpf), bank_id int not null references bank(id), initial_balance numeric(15, 2) default 0);

create table category(id serial not null primary key, type varchar(20) not null, name varchar(255) not null, user_cpf varchar(14) references users(cpf));

create table revenues(id serial not null, user_cpf varchar(14) not null references users(cpf), description varchar(200), value numeric(15, 2) not null, receipt_date date not null default current_date, category_id int not null references category(id));

create table expenses(id serial not null, user_cpf varchar(14) not null references users(cpf), description varchar(200), value numeric(15, 2) not null, expense_date date not null default current_date, category_id int not null references category(id));