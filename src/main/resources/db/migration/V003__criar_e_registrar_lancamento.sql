create table lancamento(
codigo bigint(20) primary key auto_increment,
descricao varchar(255),
valor varchar(20),
data_fiado date,
data_pagamento date,
pago boolean,
codigo_pessoa bigint(20) not null,
codigo_produto bigint(20) not null,
foreign key(codigo_pessoa) references pessoa(codigo),
foreign key(codigo_produto) references produto(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into lancamento (descricao, valor, data_fiado, data_pagamento, pago, codigo_pessoa, codigo_produto) values ('gas', '130','2022-10-20 ' ,  '2022-10-24', true, 1, 1);
insert into lancamento (descricao, valor, data_fiado, data_pagamento, pago, codigo_pessoa, codigo_produto) values ('gas', '130','2022-10-21 ', '2022-10-01', true, 2, 1);  
insert into lancamento (descricao, valor, data_fiado, data_pagamento, pago, codigo_pessoa, codigo_produto) values ('gas', '130', '2022-10-21','2022-10-01', true, 3, 1);  