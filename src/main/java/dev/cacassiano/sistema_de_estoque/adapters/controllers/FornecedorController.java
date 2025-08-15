package dev.cacassiano.sistema_de_estoque.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.services.FornecedorService;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService service;

    @PostMapping("/register")
    // No futuro enviar o token de autentificação de volta
    public ResponseEntity<Fornecedor> registerFornecedor(@RequestBody @Valid FornecedorRequestDTO request) {
        return ResponseEntity.status(201).body(this.service.create(request));
    } 
}
