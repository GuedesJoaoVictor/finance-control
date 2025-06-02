create table users(cpf varchar(14) not null primary key, name varchar(255) not null, email varchar(155) not null, password varchar(255) not null);

create table bank(id serial not null primary key, name varchar(100) not null, type varchar(100));

create table user_bank(id serial not null primary key, name varchar(100) not null, user_cpf varchar(14) not null references users(cpf), bank_id int not null references bank(id), initial_balance numeric(15, 2) default 0);

create table category(id serial not null primary key, type varchar(20) not null, name varchar(255) not null, user_cpf varchar(14) references users(cpf));

create table revenues(id serial not null, user_cpf varchar(14) not null references users(cpf), description varchar(200), value numeric(15, 2) not null, receipt_date date not null default current_date, category_id int not null references category(id), bank_id int not null references bank(id));

create table expenses(id serial not null, user_cpf varchar(14) not null references users(cpf), description varchar(200), value numeric(15, 2) not null, expense_date date not null default current_date, category_id int not null references category(id), bank_id int not null references bank(id));

insert into users(cpf, name, email, password) values ('05265295046', 'Guedes', 'guedes@email.com', '1234');

insert into bank(name, type) values ('Banco do Brasil', 'Conta Corrente');

insert into user_bank(name, user_cpf, bank_id, initial_balance) values ('Conta Corrente Banco do Brasil', '05265295046', 1, 1000.00);

insert into bank(name, type) values ('NuBank', 'Conta Corrente'),
                                    ('Itaú', 'Conta Corrente'),
                                    ('Santander', 'Conta Corrente'),
                                    ('Bradesco', 'Conta Corrente'),
                                    ('Caixa', 'Conta Corrente');

INSERT INTO users (cpf, name, email, password) VALUES
                                                   ('144.161.644-49', 'Carlos Eduardo da Luz', 'carlos.luz@example.com', 'usuario123'),
                                                   ('676.736.831-02', 'Marina da Silva Costa', 'marina.costa@example.com', 'usuario123'),
                                                   ('031.903.253-83', 'João Victor Martins', 'joao.martins@example.com', 'usuario123');

INSERT INTO user_bank (name, user_cpf, bank_id, initial_balance) VALUES
                                                                         ('Conta Banco do Brasil', '144.161.644-49', 1, 5467.89),
                                                                         ('Conta Caixa Econômica Federal', '144.161.644-49', 6, 8752.23),
                                                                         ('Conta Itaú', '144.161.644-49', 3, 3198.45),

                                                                         ('Conta Banco do Brasil', '676.736.831-02', 1, 6243.77),
                                                                         ('Conta Caixa Econômica Federal', '676.736.831-02', 2, 9287.11),
                                                                         ('Conta Itaú', '676.736.831-02', 3, 4723.99),
                                                                         ('Conta Banco do Brasil', '031.903.253-83', 1, 7622.66),
                                                                         ('Conta Caixa Econômica Federal', '031.903.253-83', 6, 8399.02),
                                                                         ('Conta Itaú', '031.903.253-83', 3, 5312.55);

INSERT INTO category (type, name, user_cpf) VALUES
                                                    ('receita', 'Salário', NULL),
                                                    ('receita', 'Investimentos', NULL),
                                                    ('despesa', 'Alimentação', NULL),
                                                    ('despesa', 'Transporte', NULL),
                                                    ('despesa', 'Moradia', NULL);

-- Para Carlos Eduardo da Luz
INSERT INTO revenues (user_cpf, description, value, receipt_date, category_id, bank_id) VALUES
                                                                                       ('144.161.644-49', 'Receita de salário fixo', 4200.50, '2025-03-10', 1, 1),
                                                                                       ('144.161.644-49', 'Pagamento mensal recebido', 3950.00, '2025-04-10', 1, 1),
                                                                                       ('144.161.644-49', 'Dividendos de ações', 870.75, '2025-04-20', 2, 1),
                                                                                       ('144.161.644-49', 'Salário adicional', 1500.00, '2025-05-10', 1, 1),
                                                                                       ('144.161.644-49', 'Recebimento de bônus', 1000.00, '2025-05-15', 1, 1);

-- Para Marina da Silva Costa
INSERT INTO revenues (user_cpf, description, value, receipt_date, category_id, bank_id) VALUES
                                                                                       ('676.736.831-02', 'Salário mensal', 4500.00, '2025-03-15', 1, 1),
                                                                                       ('676.736.831-02', 'Rendimento da poupança', 380.25, '2025-03-28', 2, 1),
                                                                                       ('676.736.831-02', 'Lucro de investimento', 1120.45, '2025-04-15', 2, 1),
                                                                                       ('676.736.831-02', 'Pagamento extra', 2100.00, '2025-05-10', 1, 1),
                                                                                       ('676.736.831-02', 'Renda de freelancer', 900.00, '2025-05-25', 1, 1);

-- Para João Victor Martins
INSERT INTO revenues (user_cpf, description, value, receipt_date, category_id, bank_id) VALUES
                                                                                       ('031.903.253-83', 'Salário mensal', 4300.00, '2025-03-12', 1, 1),
                                                                                       ( '031.903.253-83', 'Renda variável', 980.50, '2025-03-30', 2, 1),
                                                                                       ('031.903.253-83', 'Bônus de produtividade', 1300.00, '2025-04-12', 1, 1),
                                                                                       ('031.903.253-83', 'Comissão', 1750.00, '2025-05-05', 1, 1),
                                                                                       ('031.903.253-83', 'Dividendos de fundo', 640.20, '2025-05-20', 2, 1);

-- Para Carlos Eduardo da Luz
INSERT INTO expenses (user_cpf, description, value, expense_date, category_id, bank_id) VALUES
                                                                                       ('144.161.644-49', 'Supermercado mensal', 650.30, '2025-03-05', 3, 1),
                                                                                       ('144.161.644-49', 'Uber para o trabalho', 150.00, '2025-03-18', 4, 1),
                                                                                       ( '144.161.644-49', 'Aluguel', 1200.00, '2025-04-01', 5, 1),
                                                                                       ( '144.161.644-49', 'Jantar com amigos', 230.50, '2025-04-22', 3, 1),
                                                                                       ( '144.161.644-49', 'Transporte público', 180.00, '2025-05-03', 4, 1);

-- Para Marina da Silva Costa
INSERT INTO expenses (user_cpf, description, value, expense_date, category_id, bank_id) VALUES
                                                                                       ('676.736.831-02', 'Feira orgânica', 320.10, '2025-03-09', 3, 1),
                                                                                       ('676.736.831-02', 'Manutenção do carro', 850.00, '2025-03-19', 4, 1),
                                                                                       ('676.736.831-02', 'Aluguel', 1100.00, '2025-04-01', 5, 1),
                                                                                       ('676.736.831-02', 'Restaurante japonês', 275.40, '2025-04-27', 3, 1),
                                                                                       ('676.736.831-02', 'Gasolina', 400.00, '2025-05-10', 4, 1);

-- Para João Victor Martins
INSERT INTO expenses (user_cpf, description, value, expense_date, category_id, bank_id) VALUES
                                                                                       ('031.903.253-83', 'Mercado do mês', 780.00, '2025-03-03', 3, 1),
                                                                                       ('031.903.253-83', 'Ônibus mensal', 140.00, '2025-03-20', 4, 1),
                                                                                       ( '031.903.253-83', 'Aluguel', 1300.00, '2025-04-01', 5, 1),
                                                                                       ('031.903.253-83', 'Delivery de comida', 210.75, '2025-04-25', 3, 1),
                                                                                       ('031.903.253-83', 'Combustível', 365.00, '2025-05-12', 4, 1);

