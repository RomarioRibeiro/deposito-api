create table pessoa (
codigo bigint(20) primary key auto_increment,
nome varchar(80) not null,
telefone varchar(25),
numero varchar(10),
rua varchar(60),
bairro varchar(255),
ativo boolean
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into pessoa(nome, telefone, numero, rua, bairro, ativo) values ('Romario Ribeiro', '43999999', '357', 'Espanha', 'Alemanha', true);
insert into pessoa(nome, telefone, numero, rua, bairro, ativo) values ('Mario Ribeiro', '439888888', '157', 'Centro', 'Portugal', true);
insert into pessoa(nome, telefone, numero, rua, bairro, ativo) values ('Luiz da Silva', '4397777777', '457', 'Alemanha', 'Brasil', true);
insert into pessoa(nome, telefone, numero, rua, bairro, ativo) values ('João das Couves', '439666666', '100', 'Portugual', 'Canada',  true);
insert into pessoa(nome, telefone, numero, rua, bairro, ativo) values ('Maria da Silva', '4392222222', '200', 'Canada', 'Acacia', true);