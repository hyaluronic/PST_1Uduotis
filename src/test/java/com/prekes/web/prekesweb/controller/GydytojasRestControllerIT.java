package com.prekes.web.prekesweb.controller;

import com.prekes.web.prekesweb.PrekesWebApplication;
import com.prekes.web.prekesweb.model.Diagnozes;
import com.prekes.web.prekesweb.model.Gydytojas;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = PrekesWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GydytojasRestControllerIT {
    @LocalServerPort
    private int port;

    @Test
    void test() {
        System.out.println("PORT=" + port);
    }

    // 1 budas, kai negalime nustatyti requesto headeriu
    @Order(1)
    @Test
    void testRetrieveDetailsForDiagnozes1() throws Exception {
        String url = "http://localhost:" + port + "/gydytojai/6/diagnozes/1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("RESPONSE_1:" + response);
        String expected = "{\"pacientoId\":1,\"gydytojoId\":24,\"diagnoze\":\"Liga1\",\"data\":\"01.01\"}";
        JSONAssert.assertEquals(expected, response, false);
    }

    // 2 budas, kai galime nustatyti requesto headerius
    @Order(2)
    @Test
    void testRetrieveDetailsForDiagnozes2() throws Exception {
        String url = "http://localhost:" + port + "/gydytojai/6/diagnozes/1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE_2:" + response.getBody());
        String expected = "{\"pacientoId\":1,\"gydytojoId\":24,\"diagnoze\":\"Liga1\",\"data\":\"01.01\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Order(3)
    @Test
    void testFindDiagnozesForGydytojas() {
        String url = "http://localhost:" + port + "/gydytojai/24/diagnozes";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<List<Diagnozes>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<>() {
                });
        System.out.println("RESPONSE_3:" + response.getBody());
        Diagnozes sample = new Diagnozes(1, 24, "Liga1", "01.01");
        sample.setId(1);
        assertTrue(response.getBody().contains(sample));
    }

    @Order(4)
    @Test
    void testAddDiagnozeToGydytojas() {
        System.out.println("~~~ PORT=" + port);
        String url = "http://localhost:" + port + "/gydytojai/1/diagnozes";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Diagnozes diagnoze = new Diagnozes(1, 6, "Liga1", "01.01");
        diagnoze.setId(22);
        HttpEntity<Diagnozes> entity = new HttpEntity<>(diagnoze, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        System.out.println("NEW RESOURCE URI: " + actual);
        assertTrue(actual.contains("/gydytojai/1/diagnozes/22"));
    }

    @Order(5)
    @Test
    void testGydytojaiJson() throws Exception {
        String url = "http://localhost:" + port + "/gydytojai-json";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:" + response.getBody());
        String expected = "[" +
                "{\"id\":21,\"vardas\":\"Aaa\",\"telNr\":\"370111\"},\n" +
                "{\"id\":22,\"vardas\":\"Bbb\",\"telNr\":\"370222\"},\n" +
                "{\"id\":23,\"vardas\":\"Ccc\",\"telNr\":\"370333\"},\n" +
                "{\"id\":24,\"vardas\":\"Ddd\",\"telNr\":\"370444\"},\n" +
                "{\"id\":25,\"vardas\":\"Eee\",\"telNr\":\"370555\"},\n" +
                "{\"id\":26,\"vardas\":\"Fff\",\"telNr\":\"370666\"},\n" +
                "{\"id\":27,\"vardas\":\"Vardas2\",\"telNr\":\"122321\"},\n" +
                "{\"id\":28,\"vardas\":\"Vardas3\",\"telNr\":\"52323235\"},\n" +
                "{\"id\":29,\"vardas\":\"Vardas4\",\"telNr\":\"370111\"},\n" +
                "{\"id\":30,\"vardas\":\"Vardas5\",\"telNr\":\"8323328\"},\n" +
                "{\"id\":31,\"vardas\":\"Vardas6\",\"telNr\":\"370666\"},\n" +
                "{\"id\":32,\"vardas\":\"Vardas7\",\"telNr\":\"370333\"},\n" +
                "{\"id\":33,\"vardas\":\"Vardas3\",\"telNr\":\"370444\"},\n" +
                "{\"id\":34,\"vardas\":\"Vardas4\",\"telNr\":\"370222\"},\n" +
                "{\"id\":35,\"vardas\":\"Vardas5\",\"telNr\":\"370666\"}" +
                "]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Order(6)
    @Test
    void testGydytojasById() throws Exception {
        String url = "http://localhost:" + port + "/gydytojai/24";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:" + response.getBody());
        String expected = "{\"id\":24,\"vardas\":\"Ddd\",\"telNr\":\"370444\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Order(7)
    @Test
    void testAddGydytojas() {
        String url = "http://localhost:" + port + "/gydytojai";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Gydytojas gydytojas = new Gydytojas("AAAA", "123456");
        HttpEntity<Gydytojas> entity = new HttpEntity<>(gydytojas, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        System.out.println("NEW RESOURCE URI: " + actual);
        assertTrue(actual.contains("/gydytojai/37"));
    }

    @Order(8)
    @Test
    void testDeleteGydytojasById() {
        String url = "http://localhost:" + port + "/gydytojai/24";
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.delete(url, String.class);
    }
}