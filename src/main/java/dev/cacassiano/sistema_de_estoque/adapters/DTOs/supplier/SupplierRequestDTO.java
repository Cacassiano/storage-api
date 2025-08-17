package dev.cacassiano.sistema_de_estoque.adapters.DTOs.supplier;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SupplierRequestDTO {
    @NotBlank
    @Email(message="Invalid email")
    String email;
    
    @NotBlank(message = "Invalid name")
    @Length(min=3, max=120, message="The name have a invalid length")
    String name;

    @Pattern(regexp = "^\\d{11}$")
    String phone_number;

    @NotBlank
    @CNPJ(message="CNPJ does not exists")
    String cnpj;
}
