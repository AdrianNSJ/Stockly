package com.stockly.estoque.controller;

import com.stockly.estoque.entity.Fornecedor;
import com.stockly.estoque.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FornecedorController {

    @Autowired
    FornecedorRepository repository;

    // CREATE (como em AutorController)
    @PostMapping("/fornecedor")
    public ResponseEntity<Fornecedor> criar(@RequestBody Fornecedor fornecedor) {
        // Validação para o erro 'not-null' que você viu antes
        if (fornecedor.getNomeFornecedor() == null || fornecedor.getNomeFornecedor().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna 400 se o nome estiver vazio
        }
        Fornecedor salvo = repository.save(fornecedor);
        return new ResponseEntity<>(salvo, HttpStatus.OK); // O 'biblioteca' usa OK, então vamos usar OK.
    }

    // READ (como em AutorController)
    @GetMapping("/fornecedor")
    public ResponseEntity<List<Fornecedor>> listar() {
        List<Fornecedor> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT); // Retorna 204 se vazio
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // UPDATE (como em AutorController)
    @PutMapping("/fornecedor/{id}")
    public ResponseEntity<Fornecedor> atualizar(
            @PathVariable Integer id, // O ID deve ser Integer
            @RequestBody Fornecedor fornecedorAtualizado) {
        
        Optional<Fornecedor> fornecedorAntigoOpt = repository.findById(id);

        if (fornecedorAntigoOpt.isPresent()) {
            Fornecedor fornecedor = fornecedorAntigoOpt.get();
            // Atualiza os campos
            fornecedor.setNomeFornecedor(fornecedorAtualizado.getNomeFornecedor());
            fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
            
            repository.save(fornecedor);
            return new ResponseEntity<>(fornecedor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se não encontrado
    }

    // DELETE (como em AutorController)
    @DeleteMapping("/fornecedor/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) { // O ID deve ser Integer
        if (!repository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}