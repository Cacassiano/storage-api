package dev.cacassiano.sistema_de_estoque.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.supplier.SupplierCNPJDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.supplier.SupplierRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.services.SupplierService;
import dev.cacassiano.sistema_de_estoque.entities.Supplier;
import dev.cacassiano.sistema_de_estoque.handlers.exceptions.NotFoundException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/fornecedor")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @PostMapping("/register")
    public ResponseEntity<Supplier> registerFornecedor(@RequestBody @Valid SupplierRequestDTO request) {
        return ResponseEntity.status(201).body(service.create(request));
    } 

    @PutMapping("/update")
    public ResponseEntity<Supplier> updateFornecedor(@RequestBody @Valid SupplierRequestDTO request) throws NotFoundException {
        return ResponseEntity.ok().body(service.update(request));
    } 

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFornecedor(@RequestBody @Valid SupplierCNPJDTO req) throws NotFoundException {
        service.delete(req.getCnpj());
        return ResponseEntity.noContent().build();
    } 

}
