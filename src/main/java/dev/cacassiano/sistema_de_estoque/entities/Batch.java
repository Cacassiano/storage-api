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

@Table(name="batch")
@Entity(name="batch")
@Getter
@Setter
public class Batch {
    @Id @Column(name="id", nullable=false, unique= true) @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column(nullable=false, unique= false, name="batch_id") 
    Long batch_id;

    @Column(name="expiration_date", nullable=false, unique=false)
    LocalDateTime expiration_date;

    @Column(name="fabrication_date", nullable=false, unique=false)
    LocalDateTime fabrication_date;

    @Column(name="batch_amount", unique=false, nullable=false)
    int batch_amount;

    @Column(name="active", unique=false, nullable=false)
    boolean active;

    @ManyToOne
    @JoinColumn(name="supplier_cnpj")
    Supplier supplier;

    @ManyToOne
    @JoinColumn(name="product_id")
    Product product;
}
