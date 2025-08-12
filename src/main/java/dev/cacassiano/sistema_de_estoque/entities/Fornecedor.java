package dev.cacassiano.sistema_de_estoque.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Fornecedor")
@Table(name="Fornecedor")
@Getter
@Setter
public class Fornecedor {
    @Id
    @Column(name="cnpj", nullable=false, unique=true)
    String cnpj;

    @Column(name="name", nullable=false)
    String name;
    
    @Column(name="password", nullable=false, unique=false)
    String password;

    @Column(name="email", nullable=false, unique=true)
    String email;
    
    @OneToMany(mappedBy="id")
    List<Produto> produtos;
}