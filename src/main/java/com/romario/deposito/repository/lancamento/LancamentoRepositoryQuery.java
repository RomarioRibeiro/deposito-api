package com.romario.deposito.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.romario.deposito.modelo.Lancamento;
import com.romario.deposito.repository.filter.LancamentoFilter;
import com.romario.deposito.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filter(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumo(LancamentoFilter lancamentoFilter, Pageable pageable);
}
