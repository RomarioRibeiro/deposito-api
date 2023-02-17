package com.romario.deposito.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.romario.deposito.modelo.Lancamento;
import com.romario.deposito.modelo.Lancamento_;
import com.romario.deposito.modelo.Pessoa_;
import com.romario.deposito.modelo.Produto_;
import com.romario.deposito.repository.filter.LancamentoFilter;
import com.romario.deposito.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Lancamento> filter(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicate = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicate);

		TypedQuery<Lancamento> query = entityManager.createQuery(criteria);
		adicionarRegistracoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	@Override
	public Page<ResumoLancamento> resumo(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = criteriaBuilder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		criteria.select(criteriaBuilder.construct(ResumoLancamento.class, root.get(Lancamento_.codigo),
				root.get(Lancamento_.descricao), root.get(Lancamento_.data_pagamento), root.get(Lancamento_.data_fiado),
				root.get(Lancamento_.valor), root.get(Lancamento_.pago),
				root.get(Lancamento_.produto).get(Produto_.descricao), root.get(Lancamento_.pessoa).get(Pessoa_.nome)));

		Predicate[] predicates = criarRestricoes(lancamentoFilter, criteriaBuilder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoLancamento> query = entityManager.createQuery(criteria);
		adicionarRegistracoesDePaginacao(query, pageable);
		

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	private Long total(LancamentoFilter lancamentoFilter) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicate = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicate);

		criteria.select(builder.count(root));
		return entityManager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRegistracoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {

		List<Predicate> pridicates = new ArrayList<>();

		if(!ObjectUtils.isEmpty(lancamentoFilter.getNome())) {
			pridicates.add(builder.like(
					builder.lower(root.get(Lancamento_.pessoa).get(Pessoa_.nome )), "%" + lancamentoFilter.getNome() + "%"));
		}
		
		
		if(!ObjectUtils.isEmpty(lancamentoFilter.getDescricao())) {
			pridicates.add(builder.like(
					builder.lower(root.get(Lancamento_.descricao)), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}
		if (lancamentoFilter.getData_pagamentoDe() != null) {
			pridicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.data_pagamento),
					lancamentoFilter.getData_pagamentoDe()));
		}
		if (lancamentoFilter.getData_pagamentoAte() != null) {
			pridicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.data_pagamento),
					lancamentoFilter.getData_pagamentoAte()));
		}

		return pridicates.toArray(new Predicate[pridicates.size()]);
	}

}
