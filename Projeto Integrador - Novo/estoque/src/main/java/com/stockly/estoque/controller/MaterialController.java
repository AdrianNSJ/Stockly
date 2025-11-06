package com.stockly.estoque.controller;

import com.stockly.estoque.entity.Material;
import com.stockly.estoque.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MaterialController {

    @Autowired
    MaterialRepository repository;

    @PostMapping("/material")
    public ResponseEntity<Material> criar(@RequestBody Material material) {
        try {
            Material salvo = repository.save(material);
            return new ResponseEntity<>(salvo, HttpStatus.OK);
        } catch (Exception e) {
            // Isso vai falhar se o 'fornecedor' enviado não existir ou for nulo
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/material")
    public ResponseEntity<List<Material>> listar() {
        List<Material> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PutMapping("/material/{id}")
    public ResponseEntity<Material> atualizar(
            @PathVariable Integer id,
            @RequestBody Material materialAtualizado) {
        
        Optional<Material> materialAntigoOpt = repository.findById(id);

        if (materialAntigoOpt.isPresent()) {
            Material material = materialAntigoOpt.get();
            
            // Atualiza os campos
            material.setNome(materialAtualizado.getNome());
            material.setDescricao(materialAtualizado.getDescricao());
            material.setQtdEstoque(materialAtualizado.getQtdEstoque());
            material.setPrecoUnit(materialAtualizado.getPrecoUnit());
            // Atualiza a chave estrangeira
            material.setFornecedor(materialAtualizado.getFornecedor()); 
            
            repository.save(material);
            return new ResponseEntity<>(material, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/material/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}