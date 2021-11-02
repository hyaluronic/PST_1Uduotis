package com.gydytojai.web.gydytojaiweb.controller;

import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import com.gydytojai.web.gydytojaiweb.service.DiagnozesService;
import com.gydytojai.web.gydytojaiweb.service.GydytojasService;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(value = DiagnozesRestController.class)
class DiagnozesRestControllerTest {
    @MockBean
    DiagnozesService diagnozesService;
    @Autowired
    private MockMvc mockMvc; // allows to make a call to the service
    // use @MockBean to add mock objects to the Spring application context.
    @MockBean
    private GydytojasService gydytojasService;

    @Test
    void testDiagnozesJson() throws Exception {
        List<Diagnozes> diagnozes = new ArrayList<>();
        diagnozes.add(new Diagnozes(1, 1, "aaaaaaaa", "1111-11-11"));
        diagnozes.add(new Diagnozes(2, 1, "bbbbbbbb", "1111-11-12"));
        when(diagnozesService.findAll()).thenReturn(diagnozes);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-diagnozes-json")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String expected = "["
                + "{\"pacientoId\":1,\"gydytojoId\":1,\"diagnoze\":\"aaaaaaaa\",\"data\":\"1111-11-11\"},"
                + "{\"pacientoId\":2,\"gydytojoId\":1,\"diagnoze\":\"bbbbbbbb\",\"data\":\"1111-11-12\"}"
                + "]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testDiagnozesById() throws Exception {
        Diagnozes diagnozes1 = new Diagnozes(1, 1, "aaaaaaaa", "1111-11-11");
        when(diagnozesService.findById(diagnozes1.getId())).thenReturn(diagnozes1);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/diagnozes/" + diagnozes1.getId())
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String expected = "{\"pacientoId\":1,\"gydytojoId\":1,\"diagnoze\":\"aaaaaaaa\",\"data\":\"1111-11-11\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testAddDiagnozes() throws Exception {
        Diagnozes mockDiagnoze = new Diagnozes(1, 1, "aaaaaaaa", "1111-11-11");
        when(diagnozesService.add(Mockito.any(Diagnozes.class))).thenReturn(mockDiagnoze);
        String diagnozeJson = "{\"pacientoId\":1,\"gydytojoId\":1,\"diagnoze\":\"aaaaaaaa\",\"data\":\"1111-11-11\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/diagnozes")
                .accept(MediaType.APPLICATION_JSON)
                .content(diagnozeJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/diagnozes/" + mockDiagnoze.getId(), response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    void testDeleteDiagnozesById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/diagnozes/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
