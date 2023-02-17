package com.romario.deposito.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.romario.deposito.modelo.Pessoa;

public class LancamentoFilter {

	private String descricao;
	
	private String nome;
	
	private String valor;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_pagamentoAte;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_pagamentoDe;
	private boolean pago = false;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	


	public LocalDate getData_pagamentoAte() {
		return data_pagamentoAte;
	}

	public void setData_pagamentoAte(LocalDate data_pagamentoAte) {
		this.data_pagamentoAte = data_pagamentoAte;
	}

	public LocalDate getData_pagamentoDe() {
		return data_pagamentoDe;
	}

	public void setData_pagamentoDe(LocalDate data_pagamentoDe) {
		this.data_pagamentoDe = data_pagamentoDe;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

}
