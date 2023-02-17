package com.romario.deposito.modelo;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lancamento.class)
public abstract class Lancamento_ {

	public static volatile SingularAttribute<Lancamento, Long> codigo;
	public static volatile SingularAttribute<Lancamento, Pessoa> pessoa;
	public static volatile SingularAttribute<Lancamento, Produto> produto;
	public static volatile SingularAttribute<Lancamento, String> valor;
	public static volatile SingularAttribute<Lancamento, LocalDate> data_pagamento;
	public static volatile SingularAttribute<Lancamento, LocalDate> data_fiado;
	public static volatile SingularAttribute<Lancamento, Boolean> pago;
	public static volatile SingularAttribute<Lancamento, String> descricao;

	public static final String CODIGO = "codigo";
	public static final String PESSOA = "pessoa";
	public static final String PRODUTO = "produto";
	public static final String VALOR = "valor";
	public static final String DATA_PAGAMENTO = "data_pagamento";
	public static final String DATA_FIADO = "data_fiado";
	public static final String PAGO = "pago";
	public static final String DESCRICAO = "descricao";

}

