package com.prekes.web.prekesweb.repository;

import com.prekes.web.prekesweb.model.Gydytojas;
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
class GydytojasRepositoryTest {

    private static final String VARDAS = "VARDAS_1";
    private static final String TELNR_1 = "TELNR_1";
    private static final String TELNR_2 = "TELNR_2";
    @Autowired
    private GydytojasRepository gydytojasRepository;

    @Test
    void testSave() {
        Gydytojas o = new Gydytojas(VARDAS, TELNR_1);
        gydytojasRepository.save(o);
        List<Gydytojas> gydytojaiByTelNr = gydytojasRepository.findByTelNr(TELNR_1);
        assertNotNull(gydytojaiByTelNr);
        assertEquals(VARDAS, gydytojaiByTelNr.get(0).getVardas());
        System.out.println(gydytojaiByTelNr);
    }

    @Test
    void testFindAll() {
        Gydytojas o = new Gydytojas(VARDAS, TELNR_1);
        gydytojasRepository.save(o);
        Iterable<Gydytojas> gydytojai = gydytojasRepository.findAll();
        assertNotNull(gydytojai);
        List<Gydytojas> result = new ArrayList<>();
        gydytojai.forEach(result::add);
        assertEquals(1, result.size());
    }

    @Test
    void testDelete() {
        Gydytojas o = new Gydytojas(VARDAS, TELNR_1);
        gydytojasRepository.save(o);
        Gydytojas gydytojasByVardas = gydytojasRepository.findOneByVardas(VARDAS);
        assertNotNull(gydytojasByVardas);
        gydytojasRepository.delete(gydytojasByVardas);
        Iterable<Gydytojas> gydytojai = gydytojasRepository.findAll();
        List<Gydytojas> result = StreamSupport.stream(gydytojai.spliterator(), false).collect(Collectors.toList());
        assertEquals(0, result.size());
    }

    @Test
    void testfindByTelNr() {
        Gydytojas gydytojas1 = new Gydytojas(VARDAS, TELNR_1);
        Gydytojas gydytojas2 = new Gydytojas(VARDAS, TELNR_2);
        Gydytojas gydytojas3 = new Gydytojas(VARDAS, TELNR_1);
        gydytojasRepository.save(gydytojas1);
        gydytojasRepository.save(gydytojas2);
        gydytojasRepository.save(gydytojas3);
        Iterable<Gydytojas> gydytojai = gydytojasRepository.findByTelNr(TELNR_1);
        assertNotNull(gydytojai);
        List<Gydytojas> result = StreamSupport.stream(gydytojai.spliterator(), false).collect(Collectors.toList());
        assertEquals(2, result.size());
    }

    @Test
    void testFindByVardasAndTelNr() {
        Gydytojas gydytojas1 = new Gydytojas(VARDAS, TELNR_1);
        Gydytojas gydytojas2 = new Gydytojas(VARDAS, TELNR_2);
        Gydytojas gydytojas3 = new Gydytojas(VARDAS, TELNR_1);
        gydytojasRepository.save(gydytojas1);
        gydytojasRepository.save(gydytojas2);
        gydytojasRepository.save(gydytojas3);
        Iterable<Gydytojas> gydytojai = gydytojasRepository.findByVardasAndTelNr(VARDAS, TELNR_2);
        assertNotNull(gydytojai);
        List<Gydytojas> result = StreamSupport.stream(gydytojai.spliterator(), false).collect(Collectors.toList());
        assertEquals(1, result.size());
    }
}
