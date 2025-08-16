package dev.cacassiano.sistema_de_estoque.adapters.services;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;
import dev.cacassiano.sistema_de_estoque.handlers.exception.NotFoundException;

public interface FornecedorService {
    public Fornecedor create(FornecedorRequestDTO req);
    public void delete();
    public Fornecedor update(FornecedorRequestDTO req) throws NotFoundException;
}
