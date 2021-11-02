package com.gydytojai.web.gydytojaiweb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiagnozesTest {

    private static final int ID = 1;
    private static final int PACIENTO_ID = 1;
    private static final int GYDYTOJO_ID = 1;
    private static final String DIAGNOZE = "DIAGNOZE";
    private static final String DATA = "DATA";


    @Test
    void testDiagnozesConstructor() {
        Diagnozes diagnozes = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        assertAll("Test Diagnozes constructor",
                () -> assertEquals(PACIENTO_ID, diagnozes.getPacientoId()),
                () -> assertEquals(GYDYTOJO_ID, diagnozes.getGydytojoId()),
                () -> assertEquals(DIAGNOZE, diagnozes.getDiagnoze()),
                () -> assertEquals(DATA, diagnozes.getData())
        );
    }

    @Test
    void testHashCode() {
        Diagnozes diagnozes = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        diagnozes.setId(ID);
        assertEquals(ID + 31, diagnozes.hashCode());
    }

    @Test
    void testCompareTo() {
        Diagnozes diagnozes1 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        Diagnozes diagnozes2 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        assertEquals(0, diagnozes1.compareTo(diagnozes2));
    }

    @Test
    void testEqualsObject() {
        Diagnozes diagnozes1 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        Diagnozes diagnozes2 = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        assertTrue(diagnozes1.equals(diagnozes2));
    }

    @Test
    void testToString() {
        Diagnozes diagnozes = new Diagnozes(PACIENTO_ID, GYDYTOJO_ID, DIAGNOZE, DATA);
        assertEquals("Diagnoze [id =" + diagnozes.getId() + ", pacientoId=" + PACIENTO_ID + ", gydytojoId=" + GYDYTOJO_ID + ", diagnoze=" + DIAGNOZE + ", data=" + DATA + "]", diagnozes.toString());
    }

    @Test
    void testSetId() {
        Diagnozes Diagnozes = new Diagnozes();
        assertThrows(NullPointerException.class, () -> Diagnozes.setId(-1));
    }

    @Test
    void testSetPacientoId() {
        Diagnozes Diagnozes = new Diagnozes();
        assertThrows(NullPointerException.class, () -> Diagnozes.setPacientoId(-1));
    }

    @Test
    void testSetGydytojoId() {
        Diagnozes Diagnozes = new Diagnozes();
        assertThrows(NullPointerException.class, () -> Diagnozes.setGydytojoId(-1));
    }

    @Test
    void testSetDiagnoze() {
        Diagnozes Diagnozes = new Diagnozes();
        assertThrows(NullPointerException.class, () -> Diagnozes.setDiagnoze(""));
    }

    @Test
    void testSetData() {
        Diagnozes Diagnozes = new Diagnozes();
        assertThrows(NullPointerException.class, () -> Diagnozes.setData(null));
    }
}
