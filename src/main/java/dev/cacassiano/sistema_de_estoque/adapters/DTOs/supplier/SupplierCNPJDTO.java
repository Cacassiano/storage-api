package dev.cacassiano.sistema_de_estoque.adapters.DTOs.supplier;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;


public class SupplierCNPJDTO {
    @NotBlank
    @CNPJ(message="CNPJ does not exists")
    String cnpj;
    
    public String getCnpj() {
        return this.cnpj;
    }
    public SupplierCNPJDTO(String cnpj) {
        this.cnpj = cnpj;
    }
}
