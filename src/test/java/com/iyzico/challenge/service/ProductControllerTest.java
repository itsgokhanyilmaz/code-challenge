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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        productRequest.setDescription("Cell Phone");

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
        productRequest.setDescription("Cell Phone");

        payload.put("productName", productRequest.getProductName());
        payload.put("price",productRequest.getPrice().intValue());
        payload.put("stockCount", productRequest.getStockCount());
        payload.put("description", productRequest.getDescription());

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(payload.toString(), headers);
        ResponseEntity<ProductResponse> responseObj = restTemplate.postForEntity(createURLWithPort("/product/add"), httpEntity, ProductResponse.class);
        ProductResponse body = responseObj.getBody();
        assertEquals( productRequest.getProductName(), body.getProductName());
        assertEquals( productRequest.getDescription(), body.getDescription());
        assertEquals( productRequest.getPrice().intValue(), body.getPrice().intValue());
        assertEquals( productRequest.getStockCount(), body.getStockCount());

    }

    private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }

}
