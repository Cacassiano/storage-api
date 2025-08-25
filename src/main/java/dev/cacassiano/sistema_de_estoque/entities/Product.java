package dev.cacassiano.sistema_de_estoque.entities;

import java.time.LocalDateTime;
import java.util.List;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "product")
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id @Column(name="id", nullable=false, unique=true) @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @Column(name="productId", nullable=false, unique=false)
    Long productId;

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

    @Column(name="notify", unique=false)
    boolean notify;
    
    @OneToMany(mappedBy="id")
    List<Batch> batchs;

    @Column(name="created_at", nullable=false, unique=false)
    LocalDateTime created_at;

    @Column(name="updated_at", nullable=false, unique=false)
    LocalDateTime updated_at;

    @Column(name="company", nullable=false, unique=false)
    String company;
    
    public Product(ProductRequestDTO dto) {
        this.amount = 0;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.productId = dto.getProduct_id();
        this.title = dto.getTitle();
        // depois criar um enum para validar essa entradda aqui
        this.measure = dto.getMeasure();
        this.dosage = dto.getDosage();
        this.min = dto.getMin();
        this.notify = dto.getNotify();
        this.price_cents = (long)(dto.getPrice()*100);
        this.company = "teste";
    }
}
