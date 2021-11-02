package com.gydytojai.web.gydytojaiweb.service;

import com.gydytojai.web.gydytojaiweb.repository.DiagnozesRepository;
import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
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
class DiagnozesServiceTest {

    private static final int PACIENTO_ID = 1;
    private static final int GYDYTOJO_ID = 1;
    private static final String DIAGNOZE = "DIAGNOZE";
    private static final String DATA = "DATA";
    @Mock
    DiagnozesRepository repository;
    @InjectMocks
    DiagnozesService service;

    @DisplayName("Test Find All")
    @Test
    void testFindAll() {
        Diagnozes diagnoze = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        List<Diagnozes> diagnozes = new ArrayList<>();
        diagnozes.add(diagnoze);
        when(repository.findAll()).thenReturn(diagnozes);
        List<Diagnozes> found = service.findAll();
        verify(repository).findAll();
        assertEquals(1, found.size());
    }

    @Test
    void testFindById() {
        Diagnozes diagnoze = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(diagnoze));
        Diagnozes found = service.findById(1);
        verify(repository).findById(Mockito.anyInt());
        assertNotNull(found);
    }

    @Test
    void testFindBy() {
        List<Diagnozes> diagnozes = new ArrayList<>();
        diagnozes.add(new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA));
        List<Diagnozes> found = service.findByGydytojoId(GYDYTOJO_ID);
        verify(repository).findByGydytojoId(GYDYTOJO_ID);
        assertNotNull(found);
        List<Diagnozes> found1 = service.findByPacientoId(PACIENTO_ID);
        assertNotNull(found1);
        verify(repository).findByPacientoId(PACIENTO_ID);
        List<Diagnozes> found2 = service.findByDiagnoze(DIAGNOZE);
        verify(repository).findByDiagnoze(DIAGNOZE);
        assertNotNull(found2);
        List<Diagnozes> found3 = service.findByData(DATA);
        verify(repository).findByData(DATA);
        assertNotNull(found3);
    }

    @Test
    void testFindMaxId() {
        List<Diagnozes> diagnozes = new ArrayList<>();
        Diagnozes diagnoze = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        diagnoze.setId(5);
        diagnozes.add(diagnoze);
        when(repository.findAll()).thenReturn(diagnozes);
        int found = service.findMaxId();
        assertEquals(5, found);
    }

    @Test
    void testUpdate() {
        Diagnozes diagnoze = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        service.update(diagnoze);
        verify(repository).save(Mockito.any(Diagnozes.class));
    }

    @Test
    void testAdd() {
        Diagnozes diagnoze = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        when(repository.save(Mockito.any(Diagnozes.class))).thenReturn(diagnoze);

        Diagnozes added = service.add(diagnoze);
        verify(repository).save(Mockito.any(Diagnozes.class));
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
        Diagnozes diagnoze = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        service.delete(diagnoze);
        verify(repository).delete(Mockito.any(Diagnozes.class));
    }

}
