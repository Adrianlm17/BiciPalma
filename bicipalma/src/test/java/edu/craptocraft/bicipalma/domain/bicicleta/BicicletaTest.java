package edu.craptocraft.bicipalma.domain.bicicleta;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class BicicletaTest {
    
    @Test
    public void checkId(){
        Bicicleta bicicleta = new Bicicleta(291);
        assertEquals(291, bicicleta.getId());
    }

    Bicicleta bici = null;
    
    @Before
    public void setup() {
        this.bici = new Bicicleta(999);
    }

    @Test
    public void constructorBiciTest() {
        assertEquals(999, bici.getId());
    }

    @Test
    public void toStringTest() {
        assertEquals("999", bici.toString());
    }

    @Test
    public void implementationMovilTest() {
        Movil movil = bici;
        assertEquals(999, movil.getId());
    }
}
