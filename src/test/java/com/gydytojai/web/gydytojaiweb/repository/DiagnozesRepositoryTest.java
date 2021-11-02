package com.gydytojai.web.gydytojaiweb.repository;

import com.gydytojai.web.gydytojaiweb.model.Diagnozes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class DiagnozesRepositoryTest {

    private static final int PACIENTO_ID = 1;
    private static final int GYDYTOJO_ID_1 = 1;
    private static final int GYDYTOJO_ID_2 = 2;
    private static final String DIAGNOZE_1 = "DIAGNOZE_1";
    private static final String DIAGNOZE_2 = "DIAGNOZE_2";
    private static final String DATA_1 = "DATA_1";
    private static final String DATA_2 = "DATA_2";
    @Autowired
    private DiagnozesRepository diagnozesRepository;

    @Test
    void testSave() {
        Diagnozes o = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_1, DATA_1);
        diagnozesRepository.save(o);
        List<Diagnozes> gydytojaiByTelNr = diagnozesRepository.findByGydytojoId(GYDYTOJO_ID_1);
        assertNotNull(gydytojaiByTelNr);
        assertEquals(PACIENTO_ID, gydytojaiByTelNr.get(0).getPacientoId());
        System.out.println(gydytojaiByTelNr);
    }

    @Test
    void testFindAll() {
        Diagnozes o = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_1, DATA_1);
        diagnozesRepository.save(o);
        Iterable<Diagnozes> gydytojai = diagnozesRepository.findAll();
        assertNotNull(gydytojai);
        List<Diagnozes> result = new ArrayList<>();
        gydytojai.forEach(result::add);
        assertEquals(1, result.size());
    }

    @Test
    void testDelete() {
        Diagnozes o = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_1, DATA_1);
        diagnozesRepository.save(o);
        Diagnozes diagnozeByDiagnoze = diagnozesRepository.findOneByDiagnoze(DIAGNOZE_1);
        assertNotNull(diagnozeByDiagnoze);
        diagnozesRepository.delete(diagnozeByDiagnoze);
        Iterable<Diagnozes> gydytojai = diagnozesRepository.findAll();
        List<Diagnozes> result = StreamSupport.stream(gydytojai.spliterator(), false).collect(Collectors.toList());
        assertEquals(0, result.size());
    }

    @Test
    void testfindByTelNr() {
        Diagnozes diagnoze1 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_1, DATA_1);
        Diagnozes diagnoze2 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_1, DATA_1);
        Diagnozes diagnoze3 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_1, DATA_2);
        diagnozesRepository.save(diagnoze1);
        diagnozesRepository.save(diagnoze2);
        diagnozesRepository.save(diagnoze3);
        Iterable<Diagnozes> gydytojai = diagnozesRepository.findByData(DATA_1);
        assertNotNull(gydytojai);
        List<Diagnozes> result = StreamSupport.stream(gydytojai.spliterator(), false).collect(Collectors.toList());
        assertEquals(2, result.size());
    }

    @Test
    void testFindByVardasAndTelNr() {
        Diagnozes diagnoze1 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_1, DATA_1);
        Diagnozes diagnoze2 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_2, DATA_1);
        Diagnozes diagnoze3 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID_2, DIAGNOZE_1, DATA_2);
        diagnozesRepository.save(diagnoze1);
        diagnozesRepository.save(diagnoze2);
        diagnozesRepository.save(diagnoze3);
        Iterable<Diagnozes> gydytojai = diagnozesRepository.findByPacientoIdAndGydytojoIdAndDiagnoze(PACIENTO_ID, GYDYTOJO_ID_1, DIAGNOZE_1);
        assertNotNull(gydytojai);
        List<Diagnozes> result = StreamSupport.stream(gydytojai.spliterator(), false).collect(Collectors.toList());
        assertEquals(1, result.size());
    }
}
