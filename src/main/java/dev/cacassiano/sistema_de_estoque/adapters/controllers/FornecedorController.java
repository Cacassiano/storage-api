package dev.cacassiano.sistema_de_estoque.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorResquestDTO;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/fornecedor")
public class FornecedorController {
    @PostMapping("/register")
    public ResponseEntity<String> registerFornecedor(@RequestBody @Valid FornecedorResquestDTO request) {
        return ResponseEntity.ok(request.getCnpj());
    } 
}
