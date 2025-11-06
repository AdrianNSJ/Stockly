package com.stockly.estoque.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer idProduto; // INT conforme diagrama

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "preco")
    private Integer preco; // INT conforme diagrama

    @Column(name = "qtd_estoque")
    private Integer qtdEstoque; // INT conforme diagrama

    @Column(name = "estoque_id_entrada")
    private Integer estoqueIdEntrada; // INT conforme diagrama

    // Relacionamento baseado em Livro.java
    @ManyToOne
    @JoinColumn(name = "material_id_material", nullable = false)
    private Material material;
}