package com.romario.deposito.repository.pessoa;

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

import com.romario.deposito.modelo.Pessoa;
import com.romario.deposito.modelo.Pessoa_;
import com.romario.deposito.repository.filter.PessoaFilter;
import com.romario.deposito.repository.projection.ResumoPessoa;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Pessoa> filter(PessoaFilter PessoaFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);

		Predicate[] predicate = criarRestricoes(PessoaFilter, builder, root);
		criteria.where(predicate);

		TypedQuery<Pessoa> query = entityManager.createQuery(criteria);
		adicionarRegistracoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(PessoaFilter));
	}

	@Override
	public Page<ResumoPessoa> resumo(PessoaFilter PessoaFilter, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResumoPessoa> criteria = criteriaBuilder.createQuery(ResumoPessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);
		criteria.select(criteriaBuilder.construct(ResumoPessoa.class, root.get(Pessoa_.codigo),
				root.get(Pessoa_.nome), root.get(Pessoa_.rua),root.get(Pessoa_.numero) ,root.get(Pessoa_.bairro),
				root.get(Pessoa_.telefone), root.get(Pessoa_.ativo)));

		Predicate[] predicates = criarRestricoes(PessoaFilter, criteriaBuilder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoPessoa> query = entityManager.createQuery(criteria);
		adicionarRegistracoesDePaginacao(query, pageable);
		

		return new PageImpl<>(query.getResultList(), pageable, total(PessoaFilter));
	}

	private Long total(PessoaFilter PessoaFilter) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);

		Predicate[] predicate = criarRestricoes(PessoaFilter, builder, root);
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

	private Predicate[] criarRestricoes(PessoaFilter PessoaFilter, CriteriaBuilder builder,
			Root<Pessoa> root) {

		List<Predicate> pridicates = new ArrayList<>();

		if(!ObjectUtils.isEmpty(PessoaFilter.getNome())) {
			pridicates.add(builder.like(
					builder.lower(root.get(Pessoa_.nome)), "%" + PessoaFilter.getNome().toLowerCase() + "%"));
		}
		
		
		

		return pridicates.toArray(new Predicate[pridicates.size()]);
	}



}
