package com.romario.deposito.repository.projection;

import java.time.LocalDate;

public class ResumoLancamento {

	private Long codigo;
	private String descricao;
	private LocalDate data_pagamento;
	private LocalDate data_fiado;
	private String valor;
	private boolean pago = false;
	private String produto;
	private String pessoa;

	

	public ResumoLancamento(Long codigo, String descricao, LocalDate data_pagamento, LocalDate data_fiado, String valor,
			boolean pago, String produto, String pessoa) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.data_pagamento = data_pagamento;
		this.data_fiado = data_fiado;
		this.valor = valor;
		this.pago = pago;
		this.produto = produto;
		this.pessoa = pessoa;
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

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public LocalDate getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(LocalDate data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public LocalDate getData_fiado() {
		return data_fiado;
	}

	public void setData_fiado(LocalDate data_fiado) {
		this.data_fiado = data_fiado;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	

}
