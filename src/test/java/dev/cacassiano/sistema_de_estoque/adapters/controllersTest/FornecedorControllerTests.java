package dev.cacassiano.sistema_de_estoque.adapters.controllersTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import dev.cacassiano.sistema_de_estoque.adapters.controllers.FornecedorController;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;


@AutoConfigureMockMvc
@WebMvcTest(FornecedorController.class)
public class FornecedorControllerTests {
    @Autowired
    private MockMvc mvc; 
    
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void SucessefulRegister() throws Exception{
        FornecedorRequestDTO req = new FornecedorRequestDTO(
            "cassiano@gmail.com", 
            "pppppppppp", 
            "jhhhhhhhhhhhhhhhhhh", 
            "53.608.573/0001-69"
        );
        MvcResult resp = this.mvc.perform(
            post("/api/v1/fornecedor/register")
            .contentType("application/json")
            .content(mapper.writeValueAsString(req))
        ).andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType("application/json"))
        .andReturn();

        Fornecedor myFornecedor = new Fornecedor(req);
        Fornecedor resultFornecedor = mapper.convertValue(resp.getResponse().getContentAsString(), Fornecedor.class);
        assertEquals(myFornecedor.getCnpj(), resultFornecedor.getCnpj());
        assertEquals(myFornecedor.getPassword(), resultFornecedor.getPassword());
        assertEquals(myFornecedor.getName(), resultFornecedor.getName());
        assertEquals(myFornecedor.getEmail(), resultFornecedor.getEmail());
    }
}
