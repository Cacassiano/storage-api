package dev.cacassiano.sistema_de_estoque.adapters.DTOs.product;
import java.util.List;

import dev.cacassiano.sistema_de_estoque.entities.Batch;
import dev.cacassiano.sistema_de_estoque.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ProductResponseDTO {
    Long productId;

    String title;

    int amount;
    
    int min;

    int dosage;

    String measure;

    double price;

    boolean notify;
    
    List<Batch> batchs;

    String created_at;

    String updated_at;

    String company;
    
    public ProductResponseDTO(Product product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.amount = product.getAmount();
        this.min = product.getMin();
        this.dosage = product.getDosage();
        this.measure = product.getMeasure();
        this.price = product.getPrice_cents()/100;
        this.notify = product.isNotify();
        this.batchs = product.getBatchs();
        this.created_at = product.getCreated_at().toString();
        this.updated_at = product.getUpdated_at().toString();
        this.company = product.getCompany();
    }
}
