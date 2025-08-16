package dev.cacassiano.sistema_de_estoque.use_cases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.repositories.FornecedorRepository;
import dev.cacassiano.sistema_de_estoque.adapters.services.FornecedorService;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;
import dev.cacassiano.sistema_de_estoque.handlers.exception.NotFoundException;
@Service
public class FornecedorServiceImpl implements FornecedorService{

    @Autowired
    private FornecedorRepository repository;

    @Override
    public Fornecedor create(FornecedorRequestDTO req) {
        if(repository.existsById(req.getCnpj()) || repository.findbyEmail(req.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("This fornecedor already exists");
        }
        Fornecedor fornecedor = new Fornecedor(req);
        return repository.save(fornecedor);
    }

    @Override
    public void delete(String id) throws NotFoundException{
        if(!repository.existsById(id)) throw new NotFoundException("Fornecedor Does not exists");
        repository.deleteById(id);
    }

    @Override
    public Fornecedor update(FornecedorRequestDTO req) throws NotFoundException{
        if(!repository.existsById(req.getCnpj())) throw new NotFoundException("Fornecedor Does not exists");
        Fornecedor fornecedor = new Fornecedor(req);   
        repository.deleteById(req.getCnpj());     
        return repository.save(fornecedor);
    }
    
}
