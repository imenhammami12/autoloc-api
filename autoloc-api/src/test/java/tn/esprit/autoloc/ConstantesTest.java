package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantesTest {

    @Test
    void getVersionRetourneLaConstante() {
        assertEquals("1.0", Constantes.getVersion());
        assertEquals(Constantes.VERSION, Constantes.getVersion());
    }
}