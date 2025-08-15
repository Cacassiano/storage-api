package dev.cacassiano.sistema_de_estoque.adapters.DTOs;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FornecedorRequestDTO {
    @Email(message="Invalid email")
    String email;
    
    @NotBlank(message = "Invalid name")
    @Length(min=3, max=120, message="The name have a invalid length")
    String name;

    @Length(min=8, message="Invalid password")
    String password;

    @CNPJ(message="CNPJ does not exists")
    String cnpj;
}
