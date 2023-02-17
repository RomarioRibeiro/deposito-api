package com.romario.deposito.repository.projection;

public class ResumoPessoa {

	private Long codigo;
	private String nome;
	private String rua;
	private String numero;
	private String bairro;
	private String telefone;
	private boolean ativo;
	

	public ResumoPessoa(Long codigo, String nome, String rua, String numero, String bairro, String telefone,
			boolean ativo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.telefone = telefone;
		this.ativo = ativo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
