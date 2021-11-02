package com.gydytojai.web.gydytojaiweb.service;

import com.gydytojai.web.gydytojaiweb.model.Gydytojas;
import com.gydytojai.web.gydytojaiweb.repository.DiagnozesRepository;
import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import com.gydytojai.web.gydytojaiweb.repository.GydytojasRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GydytojasServiceTest {

    private static final String VARDAS = "VARDAS_1";
    private static final String TELNR = "TELNR_1";
    private static final int PACIENTO_ID = 1;
    private static final int GYDYTOJO_ID = 1;
    private static final String DIAGNOZE = "DIAGNOZE";
    private static final String DATA = "DATA";
    @Mock
    GydytojasRepository repository;
    @Mock
    DiagnozesRepository diagnozesRepository;
    @InjectMocks
    GydytojasService service;
    @Mock
    DiagnozesService diagnozesService;

    @DisplayName("Test Find All")
    @Test
    void testFindAll() {
        Gydytojas gydytojas = new Gydytojas(VARDAS, TELNR);
        List<Gydytojas> gydytojai = new ArrayList<>();
        gydytojai.add(gydytojas);
        when(repository.findAll()).thenReturn(gydytojai);
        List<Gydytojas> found = service.findAll();
        verify(repository).findAll();
        assertEquals(1, found.size());
    }

    @Test
    void testFindDiagnozesByGydytojoId() {
        List<Diagnozes> diagnozes = new ArrayList<>();
        diagnozes.add(new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA));
		when(diagnozesService.findByGydytojoId(Mockito.anyInt())).thenReturn(diagnozes);
        List<Diagnozes> found = service.findDiagnozesByGydytojoId(GYDYTOJO_ID);
		verify(diagnozesService).findByGydytojoId(Mockito.anyInt());
        assertNotNull(found);
    }

    @Test
    void testFindById() {
        Gydytojas gydytojas = new Gydytojas(VARDAS, TELNR);
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(gydytojas));
        Gydytojas found = service.findById(1);
        verify(repository).findById(Mockito.anyInt());
        assertNotNull(found);
    }

    @Test
    void testFindByTitle() {
        List<Gydytojas> gydytojai = new ArrayList<>();
        gydytojai.add(new Gydytojas(VARDAS, TELNR));
        when(repository.findAll()).thenReturn(gydytojai);
        List<Gydytojas> found = service.findByVardas("VARDAS");
        verify(repository).findAll();
        assertNotNull(found);
    }

    @Test
    void testUpdate() {
        Gydytojas gydytojas = new Gydytojas(VARDAS, TELNR);
        service.update(gydytojas);
        verify(repository).save(Mockito.any(Gydytojas.class));
    }

    @Test
    void testAdd() {
        Gydytojas gydytojas = new Gydytojas(VARDAS, TELNR);
        when(repository.save(Mockito.any(Gydytojas.class))).thenReturn(gydytojas);
        Gydytojas added = service.add(gydytojas);
        verify(repository).save(Mockito.any(Gydytojas.class));
        assertNotNull(added);
    }

    @Test
    void testDeleteById() {
        service.deleteById(1);
        verify(repository).deleteById(Mockito.anyInt());
        verify(repository).deleteById(1);
        verify(repository, never()).deleteById(2);
    }

    @Test
    void testDelete() {
        Gydytojas gydytojas = new Gydytojas(VARDAS, TELNR);
        service.delete(gydytojas);
        verify(repository).delete(Mockito.any(Gydytojas.class));
    }

}
