package dev.cacassiano.sistema_de_estoque.adapters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cacassiano.sistema_de_estoque.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCompanyAndProductId(String company, Long productId);
}