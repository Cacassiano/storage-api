package dev.cacassiano.sistema_de_estoque.adapters.controllers;

import org.apache.catalina.connector.Response;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorCNPJDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.services.FornecedorService;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;
import dev.cacassiano.sistema_de_estoque.handlers.exception.NotFoundException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService service;

    @PostMapping("/register")
    public ResponseEntity<Fornecedor> registerFornecedor(@RequestBody @Valid FornecedorRequestDTO request) {
        return ResponseEntity.status(201).body(service.create(request));
    } 

    @PutMapping("/update")
    public ResponseEntity<Fornecedor> updateFornecedor(@RequestBody @Valid FornecedorRequestDTO request) throws NotFoundException {
        return ResponseEntity.ok().body(service.update(request));
    } 

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFornecedor(@RequestBody @Valid FornecedorCNPJDTO req) throws NotFoundException {
        service.delete(req.getCnpj());
        return ResponseEntity.noContent().build();
    } 

}
