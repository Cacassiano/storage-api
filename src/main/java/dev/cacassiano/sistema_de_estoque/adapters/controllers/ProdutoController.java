package dev.cacassiano.sistema_de_estoque.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO requestDTO) {
        return ResponseEntity.ok().build();
    }
}
