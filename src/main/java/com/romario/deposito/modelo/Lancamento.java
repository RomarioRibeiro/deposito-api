package com.romario.deposito.modelo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "lancamento")
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;
	@ManyToOne
	@JoinColumn(name = "codigo_produto")
	private Produto produto;
	private String valor;
	@Column(name = "data_fiado")
	@JsonFormat(pattern =  "dd/MM/yyyy")
	private Date data_fiado = new Date() ;
	@Column(name = "data_pagamento")
	@JsonFormat(pattern =  "dd/MM/yyyy")
	private LocalDate data_pagamento;
	private boolean pago = false;

	public Lancamento() {}

	public Lancamento(Long codigo, String descricao, Pessoa pessoa, Produto produto, String valor, Date data_fiado,
			LocalDate data_pagamento, boolean pago) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.pessoa = pessoa;
		this.produto = produto;
		this.valor = valor;
		this.data_fiado = data_fiado;
		this.data_pagamento = data_pagamento;
		this.pago = pago;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pessoa getPessoa() {
		return pessoa ;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

		
	public Date getData_fiado() {
		return data_fiado;
	}

	public void setData_fiado(Date data_fiado) {
		this.data_fiado = data_fiado;
	}

	public LocalDate getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(LocalDate data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

}
