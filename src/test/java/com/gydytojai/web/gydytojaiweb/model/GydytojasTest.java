package com.gydytojai.web.gydytojaiweb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GydytojasTest {

    private static final int ID = 1;
    private static final String VARDAS = "VARDAS_1";
    private static final String TELNR = "TELNR_1";

    @Test
    void testGydytojasConstructor() {
        Gydytojas gydytojas = new Gydytojas(VARDAS, TELNR);
        assertAll("Test Gydytojas constructor",
                () -> assertEquals(VARDAS, gydytojas.getVardas()),
                () -> assertEquals(TELNR, gydytojas.getTelNr())
        );
    }

    @Test
    void testHashCode() {
        Gydytojas gydytojas = new Gydytojas(VARDAS, TELNR);
        gydytojas.setId(ID);
        assertEquals(ID + 31, gydytojas.hashCode());
    }

    @Test
    void testCompareTo() {
        Gydytojas gydytojas1 = new Gydytojas(VARDAS, TELNR);
        Gydytojas gydytojas2 = new Gydytojas(VARDAS, TELNR);
        assertEquals(0, gydytojas1.compareTo(gydytojas2));
    }

    @Test
    void testEqualsObject() {
        Gydytojas gydytojas1 = new Gydytojas(VARDAS, TELNR);
        Gydytojas gydytojas2 = new Gydytojas(VARDAS, TELNR);
        assertTrue(gydytojas1.equals(gydytojas2));
    }

    @Test
    void testToString() {
        Gydytojas gydytojas = new Gydytojas(VARDAS, TELNR);
        assertEquals("Gydytojas [id=" + gydytojas.getId() + ", vardas=" + VARDAS + ", telNr=" + TELNR + "]", gydytojas.toString());
    }

    @Test
    void testSetId() {
        Gydytojas gydytojas = new Gydytojas();
        assertThrows(NullPointerException.class, () -> gydytojas.setId(-1));
    }

    @Test
    void testSetVardas() {
        Gydytojas gydytojas = new Gydytojas();
        assertThrows(NullPointerException.class, () -> gydytojas.setVardas(""));
    }

    @Test
    void testSetTelNr() {
        Gydytojas gydytojas = new Gydytojas();
        assertThrows(NullPointerException.class, () -> gydytojas.setTelNr(null));
    }
}
