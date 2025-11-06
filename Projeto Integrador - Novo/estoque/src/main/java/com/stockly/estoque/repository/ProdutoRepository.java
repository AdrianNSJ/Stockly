package com.stockly.estoque.repository;

import com.stockly.estoque.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // Usamos Integer como ID, conforme a entidade (idProduto é Integer)
}