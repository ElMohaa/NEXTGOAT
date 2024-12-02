package org.example.next_goat.Clases;

public class Equipo {
    private int posicion;
    private String nombre;
    private int puntos;
    private String crest;


    public Equipo(int posicion, String nombre, int puntos, String crest) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.puntos = puntos;
        this.crest = crest;
    }


    public int getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }
    public String getCrest() {
        return crest;
    }
}
