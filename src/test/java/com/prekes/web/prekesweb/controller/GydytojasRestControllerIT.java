package com.prekes.web.prekesweb.controller;

import com.prekes.web.prekesweb.PrekesWebApplication;
import com.prekes.web.prekesweb.model.Diagnozes;
import com.prekes.web.prekesweb.model.Gydytojas;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = PrekesWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GydytojasRestControllerIT {
    @LocalServerPort
    private int port;

    @Test
    void test() {
        System.out.println("PORT=" + port);
    }

    // 1 budas, kai negalime nustatyti requesto headeriu
    @Test
    void testRetrieveDetailsForDiagnozes1() throws Exception {
        String url = "http://localhost:" + port + "/gydytojai/6/diagnozes/1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("RESPONSE_1:" + response);
        String expected = "{\"pacientoId\":1,\"gydytojoId\":6,\"diagnoze\":\"Liga1\",\"data\":\"01.01\"}";
        JSONAssert.assertEquals(expected, response, false);
    }

    // 2 budas, kai galime nustatyti requesto headerius
    @Test
    void testRetrieveDetailsForDiagnozes2() throws Exception {
        String url = "http://localhost:" + port + "/gydytojai/6/diagnozes/1";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE_2:" + response.getBody());
        String expected = "{\"pacientoId\":1,\"gydytojoId\":6,\"diagnoze\":\"Liga1\",\"data\":\"01.01\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void testFindDiagnozesForGydytojas() {
        String url = "http://localhost:" + port + "/gydytojai/6/diagnozes";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<List<Diagnozes>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<>() {
                });
        System.out.println("RESPONSE_3:" + response.getBody());
        Diagnozes sample = new Diagnozes(1, 6, "Liga1", "01.01");
        sample.setId(20);
        assertTrue(response.getBody().contains(sample));
    }

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

    @Test
    void testGydytojaiJson() throws Exception {
        String url = "http://localhost:" + port + "/gydytojai-json";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:" + response.getBody());
        String expected = "[{\"id\":6,\"vardas\":\"Vardas1\",\"telNr\":\"22\"}," +
                "{\"id\":7,\"vardas\":\"Vardas2\",\"telNr\":\"11\"}," +
                "{\"id\":8,\"vardas\":\"Vardas3\",\"telNr\":\"55\"}," +
                "{\"id\":9,\"vardas\":\"Vardas4\",\"telNr\":\"66\"}," +
                "{\"id\":10,\"vardas\":\"Vardas5\",\"telNr\":\"88\"}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void testGydytojasById() throws Exception {
        String url = "http://localhost:" + port + "/gydytojai/6";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:" + response.getBody());
        String expected = "{\"id\":6,\"vardas\":\"Vardas1\",\"telNr\":\"22\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    void testAddGydytojas() {
        String url = "http://localhost:" + port + "/gydytojai";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        Gydytojas gydytojas = new Gydytojas("AAAA", "123456");
        gydytojas.setId(20);
        HttpEntity<Gydytojas> entity = new HttpEntity<>(gydytojas, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        System.out.println("NEW RESOURCE URI: " + actual);
        assertTrue(actual.contains("/gydytojai/21"));
    }

    @Test
    void testDeleteGydytojasById() {
        String url = "http://localhost:" + port + "/gydytojai/6";
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.delete(url, String.class);
    }
}