package com.prekes.web.prekesweb.controller;

import com.prekes.web.prekesweb.PrekesWebApplication;
import com.prekes.web.prekesweb.model.Diagnozes;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = PrekesWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DiagnozesRestControllerIT {
    @LocalServerPort
    private int port;

    @Test
    void test() {
        System.out.println("PORT=" + port);
    }

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
                "{\"id\":1,\"pacientoId\":1,\"gydytojoId\":6,\"diagnoze\":\"Liga1\",\"data\":\"01.01\"}," +
                "{\"id\":2,\"pacientoId\":2,\"gydytojoId\":7,\"diagnoze\":\"Liga2\",\"data\":\"01.02\"}," +
                "{\"id\":3,\"pacientoId\":3,\"gydytojoId\":8,\"diagnoze\":\"Liga1\",\"data\":\"01.03\"}," +
                "{\"id\":4,\"pacientoId\":4,\"gydytojoId\":6,\"diagnoze\":\"Liga4\",\"data\":\"01.04\"}," +
                "{\"id\":5,\"pacientoId\":1,\"gydytojoId\":7,\"diagnoze\":\"Liga5\",\"data\":\"01.05\"}" +
                "]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void testDiagnozesById() throws Exception {
        String url = "http://localhost:" + port + "/diagnozes/1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:" + response.getBody());
        String expected = "{\"id\":1,\"pacientoId\":1,\"gydytojoId\":6,\"diagnoze\":\"Liga1\",\"data\":\"01.01\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void testAddDiagnozes() {
        String url = "http://localhost:" + port + "/diagnozes";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Diagnozes diagnozes = new Diagnozes(2, 3, "AAAAAAA", "01.01");
        diagnozes.setId(20);
        HttpEntity<Diagnozes> entity = new HttpEntity<>(diagnozes, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        System.out.println("NEW RESOURCE URI: " + actual);
        assertTrue(actual.contains("/diagnozes/20"));
    }

    @Test
    void testDeleteGydytojasById() {
        String url = "http://localhost:" + port + "/diagnozes/1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.delete(url, String.class);
    }
}