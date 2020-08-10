package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.ProductRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ProductControllerTest {

    @LocalServerPort
    private int portNumber;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private JSONObject payload;

    //private HttpHeaders headers = new HttpHeaders();

    @Test
    public void init() throws JSONException{

        payload = new JSONObject();
        ProductRequest productRequest = new ProductRequest();
        payload.put("name", productRequest.getProductName());
        payload.put("price",productRequest.getPrice());
        payload.put("count", productRequest.getStockCount());
        payload.put("description", productRequest.getDescription());

    }

    @Test
    public void createProduct_Success(){
        JSONObject payload = new JSONObject();


    }

}
