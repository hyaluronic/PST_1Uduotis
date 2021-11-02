package com.gydytojai.web.gydytojaiweb.controller;

import com.gydytojai.web.gydytojaiweb.model.Gydytojas;
import com.gydytojai.web.gydytojaiweb.service.GydytojasService;
import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import com.gydytojai.web.gydytojaiweb.service.DiagnozesService;
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
@WebMvcTest(value = DiagnozesController.class)
public class DiagnozesControllerTest {
    @MockBean
    GydytojasService gydytojasServiceMock;
    @MockBean
    DiagnozesService diagnozesServiceMock;
    @InjectMocks
    DiagnozesController gydytojasController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShowDiagnozesView() throws Exception {
        List<Diagnozes> diagnozes = new ArrayList<>();
        Diagnozes diagnozes1 = new Diagnozes(1, 0, "aaaaaaaa", "1111-11-11");
        diagnozes1.setId(1);
        Diagnozes diagnozes2 = new Diagnozes(2, 1, "bbbbbbbb", "1111-11-12");
        diagnozes2.setId(2);
        diagnozes.add(diagnozes1);
        diagnozes.add(diagnozes2);
        Gydytojas gydytojas1 = new Gydytojas("AAAA", "321");
        gydytojas1.setId(1);
        Gydytojas gydytojas2 = new Gydytojas("BBB", "321");
        gydytojas2.setId(2);
        when(gydytojasServiceMock.findById(Mockito.anyInt())).thenReturn(gydytojas1).thenReturn(gydytojas2);
        when(diagnozesServiceMock.findAll()).thenReturn(diagnozes);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-diagnozes")
                .accept(MediaType.TEXT_HTML);
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-diagnozes"))
                .andReturn();
    }

    @Test
    void showAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/add-diagnozes")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("diagnozes", new Gydytojas());
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("diagnozes"));
    }

    @Test
    void testAdd() throws Exception {
        when(diagnozesServiceMock.add(Mockito.any(Diagnozes.class))).thenReturn(new Diagnozes(2, 0, "bbbbbbbb", "1111-11-12"));
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-diagnozes")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("pacientoId", "2")
                .param("gydytojoId", "0")
                .param("diagnoze", "LIGA")
                .param("data", "01.01")
                .flashAttr("diagnozes", new Diagnozes());
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-diagnozes"));
        verify(diagnozesServiceMock).add(Mockito.any(Diagnozes.class));
    }

    @Test
    void testShowUpdatePage() throws Exception {
        when(diagnozesServiceMock.findById(Mockito.anyInt())).thenReturn(new Diagnozes(2, 0, "bbbbbbbb", "1111-11-12"));
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/update-diagnozes/0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("diagnozes", new Diagnozes());
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("diagnozes"));
    }

    @Test
    void testUpdate() throws Exception {
        when(diagnozesServiceMock.findById(Mockito.anyInt())).thenReturn(new Diagnozes(2, 0, "bbbbbbbb", "1111-11-12"));
        RequestBuilder rb = MockMvcRequestBuilders
                .post("/update-diagnozes/0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("pacientoId", "2")
                .param("gydytojoId", "0")
                .param("diagnoze", "LIGA")
                .param("data", "01.01")
                .flashAttr("diagnozes", new Diagnozes());
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-diagnozes"));
        verify(diagnozesServiceMock).update(Mockito.any(Diagnozes.class));
    }

    @Test
    void testDelete() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/delete-diagnozes/0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);
        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-diagnozes"));
        verify(diagnozesServiceMock).deleteById(Mockito.anyInt());
    }
}