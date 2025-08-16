package dev.cacassiano.sistema_de_estoque.adapters.DTOs;

import org.hibernate.validator.constraints.br.CNPJ;


public class FornecedorCNPJDTO {
    @CNPJ 
    String cnpj;
    public String getCnpj() {
        return this.cnpj;
    }
    public FornecedorCNPJDTO(String cnpj) {
        this.cnpj = cnpj;
    }
}
