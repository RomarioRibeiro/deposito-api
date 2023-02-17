package com.romario.deposito.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.romario.deposito.modelo.Pessoa;
import com.romario.deposito.repository.filter.PessoaFilter;
import com.romario.deposito.repository.projection.ResumoPessoa;

public interface PessoaRepositoryQuery {

	public Page<Pessoa> filter(PessoaFilter pessoaFilter , Pageable pageable);
	public Page<ResumoPessoa> resumo(PessoaFilter pessoaFilter, Pageable pageable);
}
