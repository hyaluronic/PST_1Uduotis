package com.prekes.web.prekesweb.controller;

import com.prekes.web.prekesweb.model.Diagnozes;
import com.prekes.web.prekesweb.model.Gydytojas;
import com.prekes.web.prekesweb.service.DiagnozesService;
import com.prekes.web.prekesweb.service.GydytojasService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(value = GydytojasRestController.class)
class GydytojasRestControllerTest {
    @MockBean
    DiagnozesService diagnozesService;
    @Autowired
    private MockMvc mockMvc; // allows to make a call to the service
    // use @MockBean to add mock objects to the Spring application context.
    @MockBean
    private GydytojasService gydytojasService;

    @Test
    void testGydytojaiJson() throws Exception {
        List<Gydytojas> gydytojas = new ArrayList<Gydytojas>();
        gydytojas.add(new Gydytojas("AAA", "000"));
        gydytojas.add(new Gydytojas("BBB", "111"));
        when(gydytojasService.findAll()).thenReturn(gydytojas);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/gydytojai-json")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String expected = "[{\"vardas\":\"AAA\",\"telNr\":\"000\"},{\"vardas\":\"BBB\",\"telNr\":\"111\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testAddGydytojas() throws Exception {
        Gydytojas mockGydytojas = new Gydytojas("AAA", "000");
        when(gydytojasService.add(Mockito.any(Gydytojas.class))).thenReturn(mockGydytojas);
        String gydytojasJson = "{\"vardas\":\"AAA\",\"telNr\":\"000\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/gydytojai")
                .accept(MediaType.APPLICATION_JSON)
                .content(gydytojasJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/gydytojai/" + mockGydytojas.getId(), response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    void testDeleteGydytojas() throws Exception {
        List<Diagnozes> mockDiagnozes = Arrays.asList(
                new Diagnozes(1, 1, "aaaaaaaa", "1111-11-11"),
                new Diagnozes(2, 1, "bbbbbbbb", "1111-11-12")
        );
        when(diagnozesService.findByGydytojoId(Mockito.anyInt())).thenReturn(mockDiagnozes);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/gydytojai/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void testGydytojasById() throws Exception {
        Gydytojas gydytojas = new Gydytojas("AAA", "000");
        when(gydytojasService.findById(Mockito.anyInt())).thenReturn(gydytojas);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/gydytojai/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String expected = "{\"vardas\":\"AAA\",\"telNr\":\"000\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testFindDiagnozesForGydytojas() throws Exception {
        List<Diagnozes> mockList = Arrays.asList(
                new Diagnozes(1, 1, "aaaaaaaa", "1111-11-11"),
                new Diagnozes(1, 2, "bbbbbbbb", "1111-11-12")
        );
        when(gydytojasService.findDiagnozesByGydytojoId(Mockito.anyInt())).thenReturn(mockList);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/gydytojai/1/diagnozes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "["
                + "{\"pacientoId\":1,\"gydytojoId\":1,\"diagnoze\":\"aaaaaaaa\",\"data\":\"1111-11-11\"},"
                + "{\"pacientoId\":1,\"gydytojoId\":2,\"diagnoze\":\"bbbbbbbb\",\"data\":\"1111-11-12\"}"
                + "]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testAddDiagnozeToGydytojas() throws Exception {
        Diagnozes mockDiagnoze = new Diagnozes(1, 1, "aaaaaaaa", "1111-11-11");
        when(diagnozesService.add(Mockito.any(Diagnozes.class))).thenReturn(mockDiagnoze);
        String diagnozeJson = "{\"pacientoId\":1,\"gydytojoId\":1,\"diagnoze\":\"aaaaaaaa\",\"data\":\"1111-11-11\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(
                        "/gydytojai/1/diagnozes")
                .accept(MediaType.APPLICATION_JSON)
                .content(diagnozeJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/gydytojai/1/diagnozes/" + mockDiagnoze.getId(), response.getHeader(HttpHeaders.LOCATION));
    }

    @Test
    void testRetrieveDetailsForDiagnoze() throws Exception {
        Diagnozes mockDiagnoze = new Diagnozes(1, 1, "aaaaaaaa", "1111-11-11");
        when(diagnozesService.findById(Mockito.anyInt())).thenReturn(mockDiagnoze);
        RequestBuilder rb = MockMvcRequestBuilders.get("/gydytojai/1/diagnozes/" + mockDiagnoze.getId()).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String expected = "{\"pacientoId\":1,\"gydytojoId\":1,\"diagnoze\":\"aaaaaaaa\",\"data\":\"1111-11-11\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
