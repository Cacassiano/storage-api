package dev.cacassiano.sistema_de_estoque.adapters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, String>{
    @Query(value = "SELECT * FROM fornecedor WHERE email = ?1", nativeQuery = true)
    public Optional<Fornecedor> findbyEmail(String email);
}
