package com.prekes.web.prekesweb.controller;

import com.prekes.web.prekesweb.model.Diagnozes;
import com.prekes.web.prekesweb.model.Gydytojas;
import com.prekes.web.prekesweb.service.DiagnozesService;
import com.prekes.web.prekesweb.service.GydytojasService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GydytojasController.class)
public class GydytojasControllerTest {
    @MockBean
    GydytojasService gydytojasServiceMock;
    @MockBean
    DiagnozesService diagnozesServiceMock;
    @InjectMocks
    GydytojasController gydytojasController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShowGydytojasView() throws Exception {
        List<Gydytojas> gydytojai = new ArrayList<>();
        gydytojai.add(new Gydytojas("AAAA", "321"));
        gydytojai.add(new Gydytojas("BBBB", "123"));
        when(gydytojasServiceMock.findAll()).thenReturn(gydytojai);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-gydytojai")
                .accept(MediaType.TEXT_HTML);
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-gydytojai"))
                .andReturn();
    }

    @Test
    void showAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/add-gydytojas")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("gydytojas", new Gydytojas());
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("gydytojas"));
    }

    @Test
    void testAdd() throws Exception {
        when(gydytojasServiceMock.add(Mockito.any(Gydytojas.class))).thenReturn(new Gydytojas("AAAA", "321"));
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-gydytojas")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("vardas", "BBBB")
                .param("telNr", "123")
                .flashAttr("gydytojas", new Gydytojas());
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-gydytojai"));
        verify(gydytojasServiceMock).add(Mockito.any(Gydytojas.class));
    }

    @Test
    void testShowUpdatePage() throws Exception {
        when(gydytojasServiceMock.findById(Mockito.anyInt())).thenReturn(new Gydytojas("AAAA", "321"));
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/update-gydytojas/0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("gydytojas", new Gydytojas());
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("gydytojas"));
    }

    @Test
    void testUpdate() throws Exception {
        when(gydytojasServiceMock.findById(Mockito.anyInt())).thenReturn(new Gydytojas("AAAA", "321"));
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/update-gydytojas/0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("vardas", "BBBB")
                .param("telNr", "123")
                .flashAttr("gydytojas", new Gydytojas());
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-gydytojai"));
        verify(gydytojasServiceMock).update(Mockito.any(Gydytojas.class));
    }

    @Test
    void testDelete() throws Exception {
        List<Diagnozes> diagnozesMock = new ArrayList<>();
        diagnozesMock.add(new Diagnozes(1, 0, "aaaaaaaa", "1111-11-11"));
        diagnozesMock.add(new Diagnozes(2, 0, "bbbbbbbb", "1111-11-12"));
        when(diagnozesServiceMock.findByGydytojoId(Mockito.anyInt())).thenReturn(diagnozesMock);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/delete-gydytojas/0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-gydytojai"));
        verify(gydytojasServiceMock).deleteById(Mockito.anyInt());
    }
}