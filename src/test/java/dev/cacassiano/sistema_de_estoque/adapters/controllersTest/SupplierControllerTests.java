package dev.cacassiano.sistema_de_estoque.adapters.controllersTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Map;

import org.springframework.http.HttpStatus;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.supplier.SupplierCNPJDTO;
import dev.cacassiano.sistema_de_estoque.adapters.DTOs.supplier.SupplierRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.repositories.SupplierRepository;
import dev.cacassiano.sistema_de_estoque.entities.Supplier;


@AutoConfigureMockMvc
@SpringBootTest
public class SupplierControllerTests {
    @Autowired
    private MockMvc mvc; 
    @Autowired
    private SupplierRepository fornecedorRepository;

    private final String baseUrl = "/api/v1/suppliers";

    private final ObjectMapper mapper = new ObjectMapper();

    private MvcResult sendRequest(MockHttpServletRequestBuilder req, String content) throws Exception {
        return this.mvc.perform(
            req
            .contentType("application/json")
            .content(content)
        ).andDo(print())
        .andReturn();
    }
    @Test
    public void SucessefulRegister() throws Exception{
        SupplierRequestDTO req = new SupplierRequestDTO(
            "teste@gmail.com", 
            "pppppppppp", 
            "11111111111", 
            "68.678.738/0001-46"
        );
        Supplier myFornecedor = new Supplier(req);

        MvcResult resp = sendRequest(post(this.baseUrl), mapper.writeValueAsString(req));

        assertEquals(resp.getResponse().getStatus(), HttpStatus.CREATED.value());
        Supplier resultFornecedor = mapper.readValue(resp.getResponse().getContentAsString(), Supplier.class);
        
        assertEquals(myFornecedor.getCnpj(), resultFornecedor.getCnpj());
        assertEquals(myFornecedor.getPhone_number(), resultFornecedor.getPhone_number());
        assertEquals(myFornecedor.getName(), resultFornecedor.getName());
        assertEquals(myFornecedor.getEmail(), resultFornecedor.getEmail());
        assertTrue(fornecedorRepository.existsById(myFornecedor.getCnpj()));
    }

    @Test
    public void badRequestCreate() throws Exception{
        // Invalid Cnpj
        SupplierRequestDTO requestDTO = new SupplierRequestDTO(
            "jj@gmail.com", 
            "test", 
            "11111111111", 
            "53.608.573/0001-68"
        );

        MvcResult resp = sendRequest(post(this.baseUrl), mapper.writeValueAsString(requestDTO));
        assertEquals(resp.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());

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
        SupplierRequestDTO req = new SupplierRequestDTO(
            "teste@gmail.com", 
            "pppppppppp", 
            "11111111111", 
            "75.778.349/0001-58"
        );
        fornecedorRepository.save(new Supplier(req));

        SupplierCNPJDTO requestDTO = new SupplierCNPJDTO("75.778.349/0001-58");
        
        MvcResult resp = sendRequest(delete(this.baseUrl), mapper.writeValueAsString(requestDTO));
        assertEquals(resp.getResponse().getStatus(), HttpStatus.NO_CONTENT.value());

        assertFalse(fornecedorRepository.existsById(req.getCnpj()));
    }

    @Test
    public void notFoundDelete() throws Exception {
        SupplierCNPJDTO requestDTO = new SupplierCNPJDTO("73.378.660/0001-84");

        MvcResult resp = sendRequest(delete(this.baseUrl), mapper.writeValueAsString(requestDTO));
        assertEquals(resp.getResponse().getStatus(), HttpStatus.UNPROCESSABLE_ENTITY.value());

        Map<String, String> respMap = this.mapper.readValue(
            resp.getResponse().getContentAsString(),
            new TypeReference<Map<String, String>>() {}
        );
        assertEquals(respMap.get("Message"), "Fornecedor Does not exists");
    }

    @Test
    public void successfulUpdate() throws Exception {
        SupplierRequestDTO req = new SupplierRequestDTO(
            "original@gmail.com",
            "originalName",
            "12345678901",
            "53.608.573/0001-69"
        );
        fornecedorRepository.save(new Supplier(req));

        SupplierRequestDTO updateReq = new SupplierRequestDTO(
            "updated@gmail.com",
            "updatedName",
            "10987654321",
            "53.608.573/0001-69"
        );

        MvcResult resp = sendRequest(put(this.baseUrl), mapper.writeValueAsString(updateReq));
        assertEquals(resp.getResponse().getStatus(), HttpStatus.OK.value());

        Supplier resultFornecedor = mapper.readValue(resp.getResponse().getContentAsString(), Supplier.class);

        assertEquals(updateReq.getCnpj(), resultFornecedor.getCnpj());
        assertEquals(updateReq.getPhone_number(), resultFornecedor.getPhone_number());
        assertEquals(updateReq.getName(), resultFornecedor.getName());
        assertEquals(updateReq.getEmail(), resultFornecedor.getEmail());
        assertTrue(fornecedorRepository.existsById(req.getCnpj()));
    }
    @Test
    public void badRequestUpdate() throws Exception {
        SupplierRequestDTO updateReq = new SupplierRequestDTO(
            "failgmail.com",
            "failName",
            "00000000000",
            "73.378.660/0001-84"
        );
        // Fornecedor cnpj does not e
        MvcResult resp = sendRequest(put(this.baseUrl), mapper.writeValueAsString(updateReq));
        assertEquals(resp.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());

        Map<String, String> respMap = this.mapper.readValue(
            resp.getResponse().getContentAsString(),
            new TypeReference<Map<String, String>>() {}
        );
        assertEquals(respMap.get("Message"), "Invalid arguments in request");
        assertEquals(respMap.get("Argument"), "email");
    }
}
