package dev.cacassiano.sistema_de_estoque.adapters.services;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;

public interface FornecedorService {
    public Fornecedor login(FornecedorRequestDTO req);
    public Fornecedor create(FornecedorRequestDTO req);
    public void delete();
    public Fornecedor update();
}
