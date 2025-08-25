package dev.cacassiano.sistema_de_estoque.adapters.DTOs.product;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequestDTO {
    @NotNull(message="Product_id must not be null ")
    @Min(value=1l, message="Must be at leat 1")
    Long product_id;

    @NotBlank(message = "title cant be null o blank")
    @Length(min=5)
    String title;
    
    @NotNull
    @Min(value=0l, message="Must be at leat 0")
    Integer min;

    @NotNull(message = "dosage cant be null o blank")
    @Min(value=1l, message="Must be at leat 1")
    Integer dosage;

    @NotNull(message = "notify cant be null o blank")
    Boolean notify;

    @NotBlank(message = "measure cant be null o blank")
    String measure;

    @NotNull(message="price_cents must not be null ")
    @Min(value=0l, message="Must be at leat 0")
    Double price;
}
