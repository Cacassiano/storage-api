package dev.cacassiano.sistema_de_estoque.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "product")
@Table(name="product")
@Getter
@Setter
public class Product {
    @Id @Column(name="id", nullable=false, unique=true) @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column(name="product_id", nullable=false, unique=false)
    Long product_id;

    @Column(name="title", nullable=false)
    String title;

    @Column(name="amount", unique=false, nullable=false)
    int amount;
    
    @Column(name="min", unique=false, nullable=false)
    int min;

    @Column(name="dosage", nullable=false, unique=false)
    int dosage;

    @Column(name="measure", nullable=false, unique=false)
    String measure;

    @Column(name="price_cents", unique=false)
    Long price_cents;
    
    @OneToMany(mappedBy="id")
    List<Batch> batchs;

    @Column(name="created_at", nullable=false, unique=false)
    LocalDateTime created_at;

    @Column(name="updated_at", nullable=false, unique=false)
    LocalDateTime updated_at;
    
}
