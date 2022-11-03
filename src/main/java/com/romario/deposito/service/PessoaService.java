package com.romario.deposito.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.romario.deposito.modelo.Pessoa;
import com.romario.deposito.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}
	
	
	public Pessoa buscarPorCodigo(Long codigo) {
		Pessoa pessoaSalva = pessoaRepository.findById(codigo)
					.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaSalva;
	}
	
	
	public void atualizarPessoaAtiva(Long codigo, boolean ativo) {
		Pessoa pessoaSalva = buscarPorCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
}
