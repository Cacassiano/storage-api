package dev.cacassiano.sistema_de_estoque.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.repositories.FornecedorRepository;
import dev.cacassiano.sistema_de_estoque.adapters.services.FornecedorService;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;
@Service
public class FornecedorServiceImpl implements FornecedorService{

    @Autowired
    private FornecedorRepository repository;

    @Override
    public Fornecedor login(FornecedorRequestDTO req) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Fornecedor create(FornecedorRequestDTO req) {
        Fornecedor fornecedor = new Fornecedor(req);
        return this.repository.save(fornecedor);
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Fornecedor update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
