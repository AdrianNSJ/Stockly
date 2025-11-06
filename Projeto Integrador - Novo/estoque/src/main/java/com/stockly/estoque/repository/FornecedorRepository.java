package com.stockly.estoque.repository;

import com.stockly.estoque.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    // Usamos Integer como ID, conforme a entidade (idFornecedor é Integer)
}