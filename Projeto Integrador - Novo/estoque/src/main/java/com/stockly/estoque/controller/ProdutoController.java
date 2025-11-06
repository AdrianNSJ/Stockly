package com.stockly.estoque.controller;

import com.stockly.estoque.entity.Produto;
import com.stockly.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;
    
    @PostMapping("/produto") 
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        try {
            Produto salvo = repository.save(produto);
            return new ResponseEntity<>(salvo, HttpStatus.OK);
        } catch (Exception e) {
            // Isso vai falhar se o 'material' enviado não existir ou for nulo
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);

        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Integer id,
            @RequestBody Produto produtoAtualizado) {
        
        Optional<Produto> produtoAntigoOpt = repository.findById(id);

        if (produtoAntigoOpt.isPresent()) {
            Produto produto = produtoAntigoOpt.get();
            
            // Atualiza os campos
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQtdEstoque(produtoAtualizado.getQtdEstoque());
            produto.setEstoqueIdEntrada(produtoAtualizado.getEstoqueIdEntrada());
            // Atualiza a chave estrangeira
            produto.setMaterial(produtoAtualizado.getMaterial()); 
            
            repository.save(produto);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}