package dev.cacassiano.sistema_de_estoque.adapters.controllersTest;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cacassiano.sistema_de_estoque.adapters.DTOs.product.ProductRequestDTO;
import dev.cacassiano.sistema_de_estoque.adapters.repositories.ProductRepository;
import dev.cacassiano.sistema_de_estoque.entities.Product;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductController {
    @Autowired
    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String baseUrl = "/api/v1/products";
    @Autowired
    private ProductRepository repository;

    private MvcResult sendRequest(MockHttpServletRequestBuilder req, String content) throws Exception {
        return this.mvc.perform(
            req
            .contentType("application/json")
            .content(content)
        ).andDo(print())
        .andReturn();
    }

    @Test
    @DisplayName("Create a product sucessuly")
    public void SucessulCreate() throws Exception{
        ProductRequestDTO requestDTO = new ProductRequestDTO(
            9l,
            "dipirona",
            10,
            1,
            true,
            "g",
            10.0
        );

        MvcResult res = sendRequest(post(this.baseUrl), mapper.writeValueAsString(requestDTO));
        assertEquals(res.getResponse().getStatus(), HttpStatus.CREATED.value());

        Optional<Product> optionalProd =  repository.findByCompanyAndProductId("teste", requestDTO.getProduct_id());
        assertTrue(optionalProd.isPresent());
        Product prod = optionalProd.get();

        assertEquals(prod.getPrice_cents(), (long)(requestDTO.getPrice()*100));
        assertEquals(prod.getTitle(), requestDTO.getTitle());
        assertEquals(prod.isNotify(), requestDTO.getNotify());
        assertEquals(prod.getMin(), requestDTO.getMin());
        assertEquals(prod.getDosage(), requestDTO.getDosage());
        assertEquals(prod.getMeasure(), requestDTO.getMeasure());

    }

    @Test
    @DisplayName("Don't create a product - Duplicate")
    public void InvalidCreate() throws Exception{
        ProductRequestDTO createDTO = new ProductRequestDTO(
            99l,
            "dipirona",
            10,
            1,
            true,
            "g",
            10.0
        );
        repository.save(new Product(createDTO));
        
        ProductRequestDTO requestDTO = new ProductRequestDTO(
            99l,
            "rivotril",
            50,
            500,
            false,
            "mg",
            100.0
        );
        MvcResult res = sendRequest(post(this.baseUrl), mapper.writeValueAsString(requestDTO));
        assertEquals(res.getResponse().getStatus(), HttpStatus.UNPROCESSABLE_ENTITY.value());

        Map<String, String> resMap = mapper.readValue(
            res.getResponse().getContentAsString(), 
            new TypeReference<Map<String, String>>(){}
        );

        assertEquals(resMap.get("Message"), "Product already exists");
        Product prod =  repository.findByCompanyAndProductId("teste", requestDTO.getProduct_id()).get();

        assertNotEquals(prod.getPrice_cents(), (long)(requestDTO.getPrice()*100));
        assertNotEquals(prod.getTitle(), requestDTO.getTitle());
        assertNotEquals(prod.isNotify(), requestDTO.getNotify());
        assertNotEquals(prod.getMin(), requestDTO.getMin());
        assertNotEquals(prod.getDosage(), requestDTO.getDosage());
        assertNotEquals(prod.getMeasure(), requestDTO.getMeasure());
    }
}
