package edu.craptocraft.bicipalma.domain.estacion;

public class Anclajes {
    
    
    
    private Anclaje[] anclajes;

    public Anclajes(int numAnclajes){
        this.anclajes = new Anclaje[numAnclajes];
        crearAnclajes();
    }

    private void crearAnclajes() {
        for (int i = 0; i < anclajes.length; i++) {
            this.anclajes[i] = new Anclaje();
        }
    }

    Anclaje[] anclajes(){
        return this.anclajes;
    };

    int numAnclajes(){
        return this.anclajes.length;
    }

    @Override
    public String toString(){
        return "Numero de anclajes: " + Integer.toString(numAnclajes());
    }

}
