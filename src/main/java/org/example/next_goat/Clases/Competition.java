package org.example.next_goat.Clases;

public class Competition {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name; // Devuelve el nombre para que se muestre en el ComboBox
    }
}
