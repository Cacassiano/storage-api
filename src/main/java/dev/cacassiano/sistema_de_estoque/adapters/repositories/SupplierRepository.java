package dev.cacassiano.sistema_de_estoque.adapters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cacassiano.sistema_de_estoque.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String>{
    Optional<Supplier> findByEmail(String email);
}
