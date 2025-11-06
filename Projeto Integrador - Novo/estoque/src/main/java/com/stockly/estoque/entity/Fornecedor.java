package com.stockly.estoque.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "fornecedor") // Garante o nome da tabela igual ao diagrama
@Data // Inclui @Getter, @Setter, etc.
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ideal para MySQL
    @Column(name = "id_fornecedor")
    private Integer idFornecedor; // INT conforme diagrama

    @Column(name = "nome_fornecedor", nullable = false, length = 255)
    private String nomeFornecedor;

    @Column(name = "telefone", length = 255)
    private String telefone;
}