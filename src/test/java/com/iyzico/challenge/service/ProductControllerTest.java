package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.service.impl.ProductServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private JSONObject payload;

    private HttpHeaders headers = new HttpHeaders();


    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void init() throws JSONException{

        payload = new JSONObject();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setPrice(BigDecimal.valueOf(12000));
        productRequest.setStockCount(20);
        productRequest.setProductName("iPhone");
        productRequest.setDescription("Call Phone");

        payload.put("name", productRequest.getProductName());
        payload.put("price",productRequest.getPrice());
        payload.put("count", productRequest.getStockCount());
        payload.put("description", productRequest.getDescription());

    }

    @Test
    public void createProduct_whenPostIsCalled_thenResponseBodyIsNotNull() throws JSONException{
        JSONObject payload = new JSONObject();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setPrice(BigDecimal.valueOf(12000));
        productRequest.setStockCount(20);
        productRequest.setProductName("iPhone");
        productRequest.setDescription("Call Phone");

        payload.put("name", productRequest.getProductName());
        payload.put("price",productRequest.getPrice());
        payload.put("count", productRequest.getStockCount());
        payload.put("description", productRequest.getDescription());

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(payload.toString(), headers);
        ResponseEntity<ProductResponse> responseObj = restTemplate.postForEntity(createURLWithPort("/product/add"), httpEntity, ProductResponse.class);
        ProductResponse result = responseObj.getBody();

        assertNotNull(result.getProductName());
        assertNotNull(result.getDescription());
        assertNotNull(result.getPrice());
        assertNotNull(result.getStockCount());

    }

    public void updateProduct_whenPutIsCalled_ShouldReturnUpdatedObject() throws JSONException {
        JSONObject payload = new JSONObject();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setPrice(BigDecimal.valueOf(12000));
        productRequest.setStockCount(20);
        productRequest.setProductName("iPhone");
        productRequest.setDescription("Call Phone");

        payload.put("name", productRequest.getProductName());
        payload.put("price",productRequest.getPrice());
        payload.put("count", productRequest.getStockCount());
        payload.put("description", productRequest.getDescription());

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(payload.toString(), headers);
        ResponseEntity<ProductResponse> responseObj = restTemplate.exchange(createURLWithPort("/product/add/{1}"), HttpMethod.PUT, httpEntity, ProductResponse.class);

        ProductResponse result = responseObj.getBody();

        assertEquals("iPhone", result.getProductName());
        assertEquals("Cell Phone", result.getDescription());
        assertEquals(BigDecimal.valueOf(12000), result.getPrice());
        assertEquals(20, Optional.ofNullable(result.getStockCount()));

    }

    private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }

}
