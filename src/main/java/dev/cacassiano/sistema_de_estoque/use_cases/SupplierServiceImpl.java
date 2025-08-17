package dev.cacassiano.sistema_de_estoque.use_cases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.supplier.SupplierRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.repositories.SupplierRepository;
import dev.cacassiano.sistema_de_estoque.adapters.services.SupplierService;
import dev.cacassiano.sistema_de_estoque.entities.Supplier;
import dev.cacassiano.sistema_de_estoque.handlers.exceptions.NotFoundException;

@Service
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierRepository repository;

    @Override
    public Supplier create(SupplierRequestDTO req) {
        if(repository.existsById(req.getCnpj()) || repository.findByEmail(req.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("This fornecedor already exists");
        }
        Supplier fornecedor = new Supplier(req);
        return repository.save(fornecedor);
    }

    @Override
    public void delete(String id) throws NotFoundException{
        if(!repository.existsById(id)) throw new NotFoundException("Fornecedor Does not exists");
        repository.deleteById(id);
    }

    @Override
    public Supplier update(SupplierRequestDTO req) throws NotFoundException{
        if(!repository.existsById(req.getCnpj())) throw new NotFoundException("Fornecedor Does not exists");
        Supplier fornecedor = new Supplier(req);   
        repository.deleteById(req.getCnpj());     
        return repository.save(fornecedor);
    }
    
}
