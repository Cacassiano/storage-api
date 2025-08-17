package dev.cacassiano.sistema_de_estoque.adapters.services;

import java.util.List;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductDetailsDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductRequestDTO;
import dev.cacassiano.sistema_de_estoque.entities.Product;
import dev.cacassiano.sistema_de_estoque.handlers.exceptions.NotFoundException;

public interface ProductService {
    public Product create(Product product);
    public void delete(Long product_id) throws NotFoundException;
    public Product update(Long product_id, ProductRequestDTO requestDTO) throws NotFoundException;

    public Product get(Long product_id) throws NotFoundException;
    public ProductDetailsDTO getProductDetails(Long product_id)throws NotFoundException;
    public List<Product> getAll();
}