create table produto (
codigo bigint(20) primary key auto_increment,
descricao varchar(20) not null,
preco double 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into produto (descricao, preco) values ('Gas', 110);