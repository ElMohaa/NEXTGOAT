package org.example.next_goat.Clases;

public class Competition {
    private int id;
    private String name;
    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
