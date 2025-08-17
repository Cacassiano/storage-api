package dev.cacassiano.sistema_de_estoque.adapters.services;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.supplier.SupplierRequestDTO;
import dev.cacassiano.sistema_de_estoque.entities.Supplier;
import dev.cacassiano.sistema_de_estoque.handlers.exceptions.NotFoundException;

public interface SupplierService {
    public Supplier create(SupplierRequestDTO req);
    public void delete(String id)throws NotFoundException;
    public Supplier update(SupplierRequestDTO req) throws NotFoundException;
}
