package org.example.next_goat.Clases;

public class Equipo {
    private int posicion;
    private String nombre;
    private int puntos;

    // Constructor
    public Equipo(int posicion, String nombre, int puntos) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    // Getters
    public int getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }
}
