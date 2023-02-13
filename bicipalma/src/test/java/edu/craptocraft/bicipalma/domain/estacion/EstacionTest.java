package edu.craptocraft.bicipalma.domain.estacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.craptocraft.bicipalma.domain.bicicleta.Bicicleta;
import edu.craptocraft.bicipalma.domain.tarjetausuario.TarjetaUsuario;

public class EstacionTest {
    

    @Test
    public void verEstadoEstacion(){
        Estacion estacion = new Estacion(1, "Manacor", 6);
        System.out.println("\n **** caso TEST visualizar estado de la estacion **** \n");
		estacion.consultarEstacion();
        assertEquals("id: 1 direccion: Manacor anclajes: 6", estacion.toString());
    }

    @Test
    public void verAnclajes(){
        System.out.println("\n **** caso TEST visualizar anclajes libres **** \n");
		Estacion estacion = new Estacion(1, "Manacor", 6);
        System.out.println("anclajesLibres: " + estacion.anclajesLibres());
		estacion.consultarAnclajes();
        assertEquals(6, estacion.anclajesLibres());

        estacion = new Estacion(1, "Manacor", 0);
        assertEquals(0, estacion.anclajesLibres());
    }

    @Test
    public void anclarBicicletaTest() {
        Estacion estacion = new Estacion(1, "Manacor", 6);
        estacion.anclarBicicleta(new Bicicleta(911));
        assertEquals(5, estacion.anclajesLibres());
        // cubrir branch del stream 
        estacion.anclarBicicleta(new Bicicleta(922));
        assertEquals(4, estacion.anclajesLibres());

        // cubrir else
        estacion = new Estacion(1, "Manacor", 0);
        estacion.anclarBicicleta(new Bicicleta(911));
        assertEquals(0, estacion.anclajesLibres());
    }

    @Test
    public void anclajesLibres_bici_anclada_Test() {
        
        // cubrir las dos ramas de !a.isOcupado()
        Estacion estacion = new Estacion(1, "Manacor", 1);
        estacion.anclarBicicleta(new Bicicleta(999));
        assertEquals(0, estacion.anclajesLibres());
    } 
    
    @Test
    public void leerTarjetaUsuarioTest() {
        TarjetaUsuario tarjeta = new TarjetaUsuario("yop", true);
        Estacion estacion = new Estacion(1, "Manacor", 1);
        assertTrue(estacion.leerTarjetaUsuario(tarjeta));
    }

    @Test
    public void retirarBicicletaTest() {

        // tarjeta inactiva
        Estacion estacion = new Estacion(1, "Manacor", 6);
        TarjetaUsuario tarjeta = new TarjetaUsuario("yop", false);
        estacion.retirarBicicleta(tarjeta);
        assertEquals(6, estacion.anclajesLibres());

        // tarjeta activa, no hay bicis
        tarjeta = new TarjetaUsuario("yop", true);
        estacion.retirarBicicleta(tarjeta);
        assertEquals(6, estacion.anclajesLibres());

        // tarjeta activa, hay bici
        estacion.anclarBicicleta(new Bicicleta(911));
        assertEquals(5, estacion.anclajesLibres());
        estacion.retirarBicicleta(tarjeta);
        assertEquals(6, estacion.anclajesLibres());
    }

}
