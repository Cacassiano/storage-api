package dev.cacassiano.sistema_de_estoque.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cacassiano.sistema_de_estoque.entities.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
    
}
