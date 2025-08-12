package dev.cacassiano.sistema_de_estoque.adapters.services;

import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;

public interface FornecedorService extends CRUDService<Fornecedor> {
    Fornecedor login();
}
