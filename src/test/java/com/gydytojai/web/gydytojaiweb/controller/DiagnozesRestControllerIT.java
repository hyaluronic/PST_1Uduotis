package com.gydytojai.web.gydytojaiweb.controller;

import com.gydytojai.web.gydytojaiweb.GydytojaiWebApplication;
import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = GydytojaiWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DiagnozesRestControllerIT {
    @LocalServerPort
    private int port;

    @Test
    void test() {
        System.out.println("PORT=" + port);
    }

    @Order(1)
    @Test
    void testDiagnozesJson() throws Exception {
        String url = "http://localhost:" + port + "/list-diagnozes-json";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:" + response.getBody());
        String expected = "[" +
                "{\"id\":2,\"pacientoId\":2,\"gydytojoId\":21,\"diagnoze\":\"Liga2\",\"data\":\"01.02\"},\n" +
                "{\"id\":3,\"pacientoId\":3,\"gydytojoId\":21,\"diagnoze\":\"Liga3\",\"data\":\"01.03\"},\n" +
                "{\"id\":4,\"pacientoId\":1,\"gydytojoId\":23,\"diagnoze\":\"Liga4\",\"data\":\"01.04\"},\n" +
                "{\"id\":5,\"pacientoId\":4,\"gydytojoId\":23,\"diagnoze\":\"Liga1\",\"data\":\"01.04\"},\n" +
                "{\"id\":6,\"pacientoId\":3,\"gydytojoId\":21,\"diagnoze\":\"Liga2\",\"data\":\"01.03\"},\n" +
                "{\"id\":7,\"pacientoId\":2,\"gydytojoId\":22,\"diagnoze\":\"Liga3\",\"data\":\"01.04\"},\n" +
                "{\"id\":8,\"pacientoId\":4,\"gydytojoId\":22,\"diagnoze\":\"Liga4\",\"data\":\"01.03\"},\n" +
                "{\"id\":9,\"pacientoId\":5,\"gydytojoId\":34,\"diagnoze\":\"Liga5\",\"data\":\"01.04\"},\n" +
                "{\"id\":10,\"pacientoId\":1,\"gydytojoId\":27,\"diagnoze\":\"Liga5\",\"data\":\"01.05\"},\n" +
                "{\"id\":11,\"pacientoId\":1,\"gydytojoId\":26,\"diagnoze\":\"Liga1\",\"data\":\"01.02\"},\n" +
                "{\"id\":12,\"pacientoId\":2,\"gydytojoId\":27,\"diagnoze\":\"Liga2\",\"data\":\"01.02\"},\n" +
                "{\"id\":13,\"pacientoId\":3,\"gydytojoId\":28,\"diagnoze\":\"Liga1\",\"data\":\"03.03\"},\n" +
                "{\"id\":14,\"pacientoId\":2,\"gydytojoId\":26,\"diagnoze\":\"Liga4\",\"data\":\"02.04\"},\n" +
                "{\"id\":15,\"pacientoId\":1,\"gydytojoId\":27,\"diagnoze\":\"Liga5\",\"data\":\"02.05\"},\n" +
                "{\"id\":16,\"pacientoId\":1,\"gydytojoId\":26,\"diagnoze\":\"Liga1\",\"data\":\"03.02\"},\n" +
                "{\"id\":17,\"pacientoId\":5,\"gydytojoId\":27,\"diagnoze\":\"Liga3\",\"data\":\"03.02\"},\n" +
                "{\"id\":18,\"pacientoId\":3,\"gydytojoId\":28,\"diagnoze\":\"Liga2\",\"data\":\"02.03\"},\n" +
                "{\"id\":19,\"pacientoId\":1,\"gydytojoId\":26,\"diagnoze\":\"Liga4\",\"data\":\"01.04\"},\n" +
                "{\"id\":20,\"pacientoId\":1,\"gydytojoId\":27,\"diagnoze\":\"Liga1\",\"data\":\"01.05\"},\n" +
                "{\"id\":36,\"pacientoId\":1,\"gydytojoId\":6,\"diagnoze\":\"Liga1\",\"data\":\"01.01\"}" +
                "]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Order(2)
    @Test
    void testDiagnozesById() throws Exception {
        String url = "http://localhost:" + port + "/diagnozes/2";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:" + response.getBody());
        String expected = "{\"id\":2,\"pacientoId\":2,\"gydytojoId\":21,\"diagnoze\":\"Liga2\",\"data\":\"01.02\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Order(3)
    @Test
    void testAddDiagnozes() {
        String url = "http://localhost:" + port + "/diagnozes";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Diagnozes diagnozes = new Diagnozes(2, 3, "AAAAAAA", "01.01");
        HttpEntity<Diagnozes> entity = new HttpEntity<>(diagnozes, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        System.out.println("NEW RESOURCE URI: " + actual);
        assertTrue(actual.contains("/diagnozes/38"));
    }

    @Order(4)
    @Test
    void testDeleteGydytojasById() {
        String url = "http://localhost:" + port + "/diagnozes/1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.delete(url, String.class);
    }
}