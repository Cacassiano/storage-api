package dev.cacassiano.sistema_de_estoque.adapters.DTOs;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;


public class FornecedorCNPJDTO {
    @NotBlank
    @CNPJ(message="CNPJ does not exists")
    String cnpj;
    
    public String getCnpj() {
        return this.cnpj;
    }
    public FornecedorCNPJDTO(String cnpj) {
        this.cnpj = cnpj;
    }
}
