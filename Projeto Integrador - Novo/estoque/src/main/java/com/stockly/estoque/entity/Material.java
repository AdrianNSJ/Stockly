package com.stockly.estoque.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "material")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Integer idMaterial; // INT conforme diagrama

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "qtd_estoque")
    private Double qtdEstoque; // DOUBLE conforme diagrama

    @Column(name = "preco_unit")
    private Double precoUnit; // DOUBLE conforme diagrama

    // Relacionamento baseado em Livro.java
    @ManyToOne
    @JoinColumn(name = "fornecedor_id_fornecedor", nullable = false)
    private Fornecedor fornecedor;
}