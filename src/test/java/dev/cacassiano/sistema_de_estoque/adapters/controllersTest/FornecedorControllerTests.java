package dev.cacassiano.sistema_de_estoque.adapters.controllersTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorCNPJDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.FornecedorRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.repositories.FornecedorRepository;
import dev.cacassiano.sistema_de_estoque.entities.Fornecedor;


@AutoConfigureMockMvc
@SpringBootTest
public class FornecedorControllerTests {
    @Autowired
    private MockMvc mvc; 
    @Autowired
    private FornecedorRepository fornecedorRepository;

    private final String baseUrl = "/api/v1/fornecedor";

    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    public void SucessefulRegister() throws Exception{
        FornecedorRequestDTO req = new FornecedorRequestDTO(
            "teste@gmail.com", 
            "pppppppppp", 
            "11111111111", 
            "68.678.738/0001-46"
        );
        Fornecedor myFornecedor = new Fornecedor(req);

        MvcResult resp = this.mvc.perform(
            post(this.baseUrl+"/register")
            .contentType("application/json")
            .content(mapper.writeValueAsString(req))
        ).andDo(print())
        .andExpect(status().isCreated())
        .andReturn();

        Fornecedor resultFornecedor = mapper.readValue(resp.getResponse().getContentAsString(), Fornecedor.class);
        
        assertEquals(myFornecedor.getCnpj(), resultFornecedor.getCnpj());
        assertEquals(myFornecedor.getPhone_number(), resultFornecedor.getPhone_number());
        assertEquals(myFornecedor.getName(), resultFornecedor.getName());
        assertEquals(myFornecedor.getEmail(), resultFornecedor.getEmail());
        assertTrue(fornecedorRepository.existsById(myFornecedor.getCnpj()));
    }

    @Test
    public void badRequestCreate() throws Exception{
        // Invalid Cnpj
        FornecedorRequestDTO requestDTO = new FornecedorRequestDTO(
            "jj@gmail.com", 
            "test", 
            "11111111111", 
            "53.608.573/0001-68"
        );
        
        MvcResult resp = this.mvc.perform(
            post(baseUrl+"/register")
            .content(this.mapper.writeValueAsString(requestDTO))
            .contentType("application/json")
        ).andExpect(status().isBadRequest())
        .andDo(print())
        .andReturn();
        Map<String, String> respMap = this.mapper.readValue(
            resp.getResponse().getContentAsString(), 
            new TypeReference<Map<String, String>>(){}
        );

        assertEquals(respMap.get("Message"), "Invalid arguments in request");
        assertEquals(respMap.get("Argument"), "cnpj");
        assertFalse(fornecedorRepository.existsById(requestDTO.getCnpj()));
    }
    
    @Test
    public void SucessulDelete() throws Exception{
        FornecedorRequestDTO req = new FornecedorRequestDTO(
            "teste@gmail.com", 
            "pppppppppp", 
            "11111111111", 
            "75.778.349/0001-58"
        );
        fornecedorRepository.save(new Fornecedor(req));

        FornecedorCNPJDTO requesDTO = new FornecedorCNPJDTO("75.778.349/0001-58");
        this.mvc.perform(
            delete(baseUrl+"/delete")
            .content(this.mapper.writeValueAsString(requesDTO))
            .contentType("application/json")
        ).andExpect(status().isNoContent());

        assertFalse(fornecedorRepository.existsById(req.getCnpj()));
    }

    @Test
    public void notFoundDelete() throws Exception {
        FornecedorCNPJDTO requesDTO = new FornecedorCNPJDTO("73.378.660/0001-84");
        MvcResult resp = this.mvc.perform(
            delete(baseUrl + "/delete")
            .content(this.mapper.writeValueAsString(requesDTO))
            .contentType("application/json")
        ).andExpect(status().isInternalServerError())
        .andDo(print())
        .andReturn();

        Map<String, String> respMap = this.mapper.readValue(
            resp.getResponse().getContentAsString(),
            new TypeReference<Map<String, String>>() {}
        );
        assertEquals(respMap.get("Message"), "Fornecedor Does not exists");
    }

    @Test
    public void successfulUpdate() throws Exception {
        FornecedorRequestDTO req = new FornecedorRequestDTO(
            "original@gmail.com",
            "originalName",
            "12345678901",
            "53.608.573/0001-69"
        );
        fornecedorRepository.save(new Fornecedor(req));

        FornecedorRequestDTO updateReq = new FornecedorRequestDTO(
            "updated@gmail.com",
            "updatedName",
            "10987654321",
            "53.608.573/0001-69"
        );

        MvcResult resp = this.mvc.perform(
            put(baseUrl + "/update")
            .content(this.mapper.writeValueAsString(updateReq))
            .contentType("application/json")
        ).andExpect(status().isOk())
        .andDo(print())
        .andReturn();

        Fornecedor resultFornecedor = mapper.readValue(resp.getResponse().getContentAsString(), Fornecedor.class);

        assertEquals(updateReq.getCnpj(), resultFornecedor.getCnpj());
        assertEquals(updateReq.getPhone_number(), resultFornecedor.getPhone_number());
        assertEquals(updateReq.getName(), resultFornecedor.getName());
        assertEquals(updateReq.getEmail(), resultFornecedor.getEmail());
        assertTrue(fornecedorRepository.existsById(req.getCnpj()));
    }

    @Test
    public void badRequestUpdate() throws Exception {
        FornecedorRequestDTO updateReq = new FornecedorRequestDTO(
            "failgmail.com",
            "failName",
            "00000000000",
            "73.378.660/0001-84"
        );
        // Fornecedor cnpj does not e
        MvcResult resp = this.mvc.perform(
            put(baseUrl + "/update")
            .content(this.mapper.writeValueAsString(updateReq))
            .contentType("application/json")
        ).andExpect(status().isBadRequest())
        .andDo(print())
        .andReturn();

        Map<String, String> respMap = this.mapper.readValue(
            resp.getResponse().getContentAsString(),
            new TypeReference<Map<String, String>>() {}
        );
        assertEquals(respMap.get("Message"), "Invalid arguments in request");
        assertEquals(respMap.get("Argument"), "email");
    }
}
