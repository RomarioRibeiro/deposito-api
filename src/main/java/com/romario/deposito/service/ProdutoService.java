package com.romario.deposito.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.romario.deposito.modelo.Produto;
import com.romario.deposito.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public Produto atualizarProduto(Long codigo, Produto produto) {
		Produto produtoSalvo = buscarProdutoPorCodigo(codigo);
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
		
		return produtoRepository.save(produtoSalvo);
	}
	
	public Produto buscarProdutoPorCodigo(Long codigo) {
		Produto produtoSalva = produtoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		
		return produtoSalva;
	}
	
	
}
