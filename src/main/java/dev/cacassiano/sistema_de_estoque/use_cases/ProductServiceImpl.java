package dev.cacassiano.sistema_de_estoque.use_cases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductDetailsDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.repositories.ProductRepository;
import dev.cacassiano.sistema_de_estoque.adapters.services.ProductService;
import dev.cacassiano.sistema_de_estoque.entities.Product;
import dev.cacassiano.sistema_de_estoque.handlers.exceptions.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;
	@Override
	public Product create(Product product) {
		repository.findByInstitutionAndProductId(product.getInstitution(), product.getProductId())
            .ifPresent(existingProduct -> {
                throw new DataIntegrityViolationException("Product already exists");
            });
        return repository.save(product);
	}

	@Override
	public void delete(Long product_id) throws NotFoundException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

	@Override
	public Product update(Long product_id, ProductRequestDTO requestDTO) throws NotFoundException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public Product get(Long product_id) throws NotFoundException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'get'");
	}

	@Override
	public ProductDetailsDTO getProductDetails(Long product_id) throws NotFoundException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getProductDetails'");
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAll'");
	}

}
