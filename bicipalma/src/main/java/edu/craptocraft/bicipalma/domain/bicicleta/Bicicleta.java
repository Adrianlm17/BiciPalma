package edu.craptocraft.bicipalma.domain.bicicleta;

public class Bicicleta implements Movil{

    private final int id;

    public Bicicleta(int id) {
        this.id = id;

    }
    
    public int getId(){
        return this.id;
    }

    @Override
    public String toString(){
        return null;
    }
}
