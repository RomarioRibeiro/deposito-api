package com.romario.deposito.repository.projection;

import java.time.LocalDate;
import java.util.Date;

public class ResumoLancamento {

	private Long codigo;
	private String descricao;
	private String pessoa;
	private String produto;
	private String valor;
	private Date data_Fiado;
	private LocalDate data_Pagamento;
	private boolean pago = false;

	public ResumoLancamento(Long codigo, String descricao, String pessoa, String produto, String valor, Date data_Fiado,
			LocalDate data_Pagamento, boolean pago) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.pessoa = pessoa;
		this.produto = produto;
		this.valor = valor;
		this.data_Fiado = data_Fiado;
		this.data_Pagamento = data_Pagamento;
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

	public Date getData_Fiado() {
		return data_Fiado;
	}

	public void setData_Fiado(Date data_Fiado) {
		this.data_Fiado = data_Fiado;
	}

	public LocalDate getData_Pagamento() {
		return data_Pagamento;
	}

	public void setData_Pagamento(LocalDate data_Pagamento) {
		this.data_Pagamento = data_Pagamento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

}
