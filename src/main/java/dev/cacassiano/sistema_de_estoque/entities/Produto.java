package dev.cacassiano.sistema_de_estoque.entities;

import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Produto")
@Table(name="Produto")
@Getter
@Setter
public class Produto {
    @Id @Column(name="id", nullable=false, unique=true) @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column(name="title", nullable=false)
    String title;

    @Column(name="amount", unique=false)
    int amount;

    @Column(name="price_cents", unique=false)
    BigInteger price_cents;

    @Column(name="created_at", nullable=false, unique=false)
    LocalDateTime created_at;

    @Column(name="updated_at", nullable=false, unique=false)
    LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name="fornecedor_id")
    Fornecedor fornecedor;
}
