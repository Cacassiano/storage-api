package dev.cacassiano.sistema_de_estoque.adapters.controllersTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import dev.cacassiano.sistema_de_estoque.adapters.controllers.FornecedorController;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.services.FornecedorService;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;


@AutoConfigureMockMvc
@WebMvcTest(FornecedorController.class)
public class FornecedorControllerTests {
    @Autowired
    private MockMvc mvc; 
    @MockitoBean
    private FornecedorService fornecedorService;
    private final String baseUrl = "/api/v1/fornecedor";

    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void SucessefulRegister() throws Exception{
        FornecedorRequestDTO req = new FornecedorRequestDTO(
            "teste@gmail.com", 
            "pppppppppp", 
            "121111111111", 
            "53.608.573/0001-69"
        );
        Fornecedor myFornecedor = new Fornecedor(req);

        when(fornecedorService.create(any())).thenReturn(myFornecedor);
        MvcResult resp = this.mvc.perform(
            post(this.baseUrl+"/register")
            .contentType("application/json")
            .content(mapper.writeValueAsString(req))
        ).andDo(print())
        .andExpect(status().isCreated())
        .andReturn();

        Fornecedor resultFornecedor = mapper.readValue(resp.getResponse().getContentAsString(), Fornecedor.class);
        
        verify(fornecedorService, times(1)).create(any());

        assertEquals(myFornecedor.getCnpj(), resultFornecedor.getCnpj());
        assertEquals(myFornecedor.getPhone_number(), resultFornecedor.getPhone_number());
        assertEquals(myFornecedor.getName(), resultFornecedor.getName());
        assertEquals(myFornecedor.getEmail(), resultFornecedor.getEmail());
    }
}
