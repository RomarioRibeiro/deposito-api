package com.romario.deposito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.romario.deposito.modelo.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
