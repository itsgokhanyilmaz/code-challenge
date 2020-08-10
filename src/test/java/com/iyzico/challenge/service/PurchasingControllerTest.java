package com.iyzico.challenge.service;

import com.iyzico.challenge.dto.PurchasingRequest;
import com.iyzico.challenge.dto.PurchasingResponse;
import com.iyzico.challenge.service.impl.PurchasingServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PurchasingControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private JSONObject payload;

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
    public void purchaseProduct_whenPostIsCalled_shouldReturnPurchasingObject() throws JSONException {
        payload = new JSONObject();
        PurchasingRequest purchasingRequest = new PurchasingRequest();
        purchasingRequest.setProductId(Long.valueOf(1223334444));
        purchasingRequest.setProductCount(2);

        payload.put("productId", purchasingRequest.getProductId());
        payload.put("productCount",purchasingRequest.getProductCount());

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(payload.toString(), headers);
        ResponseEntity<PurchasingResponse> responseObj = restTemplate.postForEntity(createURLWithPort("/purchasing/product"), httpEntity, PurchasingResponse.class);
        PurchasingResponse result = responseObj.getBody();

        assertNotNull(result.getProductId());
        assertNotNull(result.getProductCount());

    }

    private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }
}
