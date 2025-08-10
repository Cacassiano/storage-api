package dev.cacassiano.sistema_de_estoque.entities;

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

@Entity(name = "Movimento")
@Table(name="Movimento")
@Getter
@Setter
public class Movimento {
    @Id @Column(name="id", nullable=false, unique=true) @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id; 

    @Column(name="type", nullable=false, unique=false)
    String type;

    @Column(name="amount", nullable=false, unique=false)
    int amount;

    @ManyToOne
    @JoinColumn(name="issuer_id")
    Fornecedor issuer;

    @Column(name="issued_at", nullable=false, unique=false)
    LocalDateTime issued_at;
}