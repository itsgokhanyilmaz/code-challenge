package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.ProductRequest;
import com.iyzico.challenge.dto.ProductResponse;
import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.repository.ProductRepository;
import com.iyzico.challenge.service.impl.PurchasingServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PurchasingControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Mock
    private ProductRepository productRepository;

    private JSONObject payload;
    private JSONObject payload1;

    private HttpHeaders headers = new HttpHeaders();

    @InjectMocks
    private PurchasingServiceImpl purchasingService;

    @Test
    public void init() throws JSONException {

        payload = new JSONObject();
        PurchasingRequest purchasingRequest = new PurchasingRequest();
        purchasingRequest.setProductId(Long.valueOf(1223334444));
        purchasingRequest.setProductCount(2);

        payload.put("productId", purchasingRequest.getProductId());
        payload.put("productCount",purchasingRequest.getProductCount());

    }

    @Test
    public void purchaseProduct_whenPostIsCalled_shouldReturnProductNotFoundException() throws JSONException {
        payload = new JSONObject();
        PurchasingRequest purchasingRequest = new PurchasingRequest();
        purchasingRequest.setProductId(Long.valueOf(1223334444));
        purchasingRequest.setProductCount(2);

        payload.put("productId", purchasingRequest.getProductId());
        payload.put("productCount",purchasingRequest.getProductCount());

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(payload.toString(), headers);
        ResponseEntity<String> objectResponseEntity = restTemplate.postForEntity(
                createURLWithPort("/purchasing/product"), httpEntity,
                String.class);
        String body = objectResponseEntity.getBody();
        assertEquals("Product not found", body);
    }


    @Test
    public void purchaseProduct_whenPostIsCalled_shouldReturnPurchasingObject() throws JSONException {
        payload = new JSONObject();
        payload1 = new JSONObject();
        PurchasingRequest purchasingRequest = new PurchasingRequest();
        purchasingRequest.setProductId(Long.valueOf(1223334444));
        purchasingRequest.setProductCount(2);

        payload.put("productId", purchasingRequest.getProductId());
        payload.put("productCount",purchasingRequest.getProductCount());

        // product request atıldı product eklendi
        ProductRequest productRequest = new ProductRequest();
        productRequest.setPrice(BigDecimal.valueOf(12000));
        productRequest.setStockCount(20);
        productRequest.setProductName("iPhone");
        productRequest.setDescription("Cell Phone");

        payload1.put("productName", productRequest.getProductName());
        payload1.put("price",productRequest.getPrice().intValue());
        payload1.put("stockCount", productRequest.getStockCount());
        payload1.put("description", productRequest.getDescription());

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity1 = new HttpEntity<>(payload1.toString(), headers);
        ResponseEntity<ProductResponse> responseObj = restTemplate.postForEntity(
                createURLWithPort("/product/add"), httpEntity1, ProductResponse.class);
        ProductResponse body1 = responseObj.getBody();

        HttpEntity<?> httpEntity2 = new HttpEntity<>(payload.toString(), headers);
        ResponseEntity<PurchasingResponse> objectResponseEntity = restTemplate.postForEntity(
                createURLWithPort("/purchasing/product"), httpEntity2, PurchasingResponse.class);
        PurchasingResponse body2= objectResponseEntity.getBody();

        assertEquals(body1.getId(), body2.getProductId());
    }

    private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }
}
