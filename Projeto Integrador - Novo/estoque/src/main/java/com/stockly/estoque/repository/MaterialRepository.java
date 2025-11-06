package com.stockly.estoque.repository;

import com.stockly.estoque.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    // Usamos Integer como ID, conforme a entidade (idMaterial é Integer)
}