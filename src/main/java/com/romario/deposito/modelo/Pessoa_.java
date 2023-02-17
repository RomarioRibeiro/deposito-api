package com.romario.deposito.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, Long> codigo;
	public static volatile SingularAttribute<Pessoa, String> telefone;
	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
	public static volatile SingularAttribute<Pessoa, String> numero;
	public static volatile SingularAttribute<Pessoa, String> bairro;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> rua;

	public static final String CODIGO = "codigo";
	public static final String TELEFONE = "telefone";
	public static final String ATIVO = "ativo";
	public static final String NUMERO = "numero";
	public static final String BAIRRO = "bairro";
	public static final String NOME = "nome";
	public static final String RUA = "rua";

}

