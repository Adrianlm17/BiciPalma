package edu.craptocraft.bicipalma.domain.estacion;

import java.util.Arrays;
import java.util.Optional;

import edu.craptocraft.bicipalma.domain.bicicleta.Movil;
import edu.craptocraft.bicipalma.domain.tarjetausuario.Autenticacion;

public class Estacion {
    
    private final int id;
    private final String direccion;
    private final Anclajes anclajes;

    public Estacion(int id, String direccion, int numAnclajes) {
        this.id = id;
        this.direccion = direccion;
        this.anclajes = new Anclajes(numAnclajes);
    }

    private int getId(){
        return this.id;
    }

    private String getDireccion(){
        return this.direccion;
    }

    @Override
    public String toString(){
        return "id: " + getId() + " direccion: " + getDireccion() + " anclajes: " + numAnclajes();
    }
    
    private Anclaje[] anclajes(){
        return this.anclajes.anclajes();

    }

    private int numAnclajes(){
        return this.anclajes.numAnclajes();
    }
    
    public void consultarEstacion() {
        System.out.println(this);
    }

    public int anclajesLibres() {
        int anclajeLibre = 0;

        for (int i = 0; i < this.anclajes.numAnclajes(); i++){
            if (this.anclajes.anclajes()[i] == null) {
                anclajeLibre += 1;
            }
        }

        return anclajeLibre;
    }

    public void anclarBicicleta(Movil bici) {
        Optional<Anclaje> anclajeLibre = Arrays.stream(anclajes()).filter(a -> !a.isOcupado()).findAny();

		if (anclajeLibre.isPresent()) {
			anclajeLibre.get().anclarBici(bici);
		} else {
			System.out.println("No existen anclajes disponibles para bici " + bici);
		}
    }

    public boolean leerTarjetaUsuario(Autenticacion tarjetaUsuario) {
		return tarjetaUsuario.isActivada();
	}

    public void retirarBicicleta(Autenticacion tarjetaUsuario) {

		if (leerTarjetaUsuario(tarjetaUsuario)) {

			Optional<Anclaje> anclajeOcupado = Arrays.stream(anclajes()).filter(Anclaje::isOcupado).findAny();

			if (anclajeOcupado.isPresent()) {

				mostrarBicicleta(anclajeOcupado.get().getBici());
				anclajeOcupado.get().liberarBici();

			} else {
				System.out.println("No hay bicis");
			}

		} else {
			System.out.println("Tarjeta de usuario inactiva!");
		}
	}

    private void mostrarBicicleta(Movil bici){
        System.out.println("biciceleta retirada: " + bici.getId());
    }

    public void consultarAnclajes() {
        Arrays.stream(anclajes()).map(a -> Optional.ofNullable(a.getBici())).forEach(bici -> System.out.print("Anclaje" + (bici.isPresent()? bici.get(): "libre") + '\n'));
    }
    
}
