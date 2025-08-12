package dev.cacassiano.sistema_de_estoque.adapters.controllersTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import dev.cacassiano.sistema_de_estoque.adapters.controllers.FornecedorController;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorResquestDTO;


@AutoConfigureMockMvc
@WebMvcTest(FornecedorController.class)
public class FornecedorControllerTests {
    @Autowired
    private MockMvc mvc; 
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void SucessefulRegister() throws Exception{
        FornecedorResquestDTO req = new FornecedorResquestDTO(
            "cassiano@", 
            "pppppppppp", 
            "jhhhhhhhhhhhhhhhhhh", 
            "98.969.248/0001-61"
        );
        this.mvc.perform(
            post("/api/v1/fornecedor/register")
            .contentType("application/json")
            .content(mapper.writeValueAsString(req))
        ).andDo(print())
        .andExpect(status().isBadRequest());
    }
}
