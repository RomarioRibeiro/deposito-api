package com.romario.deposito.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.romario.deposito.modelo.Lancamento;
import com.romario.deposito.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	
	
	public Lancamento buscarPorCodigo(Long codigo) {
		Lancamento lancamentoSalva = lancamentoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return lancamentoSalva;
			
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalva = buscarPorCodigo(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoSalva, "codigo");
		
		
		return lancamentoRepository.save(lancamentoSalva);
	}
	
	public void atualizarLancamentoAtivo(Long codigo, boolean pago) {
		Lancamento lancamentoSalvar = buscarPorCodigo(codigo);
		lancamentoSalvar.setPago(pago);
		lancamentoRepository.save(lancamentoSalvar);
	}
	
}
