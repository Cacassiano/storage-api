package dev.cacassiano.sistema_de_estoque.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductResponseDTO;
import dev.cacassiano.sistema_de_estoque.adapters.services.ProductService;
import dev.cacassiano.sistema_de_estoque.entities.Product;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO requestDTO) {
        Product prod = productService.create(new Product(requestDTO));
        return ResponseEntity.status(201)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new ProductResponseDTO(prod));
    }
}
