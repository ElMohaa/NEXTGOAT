package org.example.next_goat.Exceptios;

public class SurnameIllegalException extends Exception{
    public SurnameIllegalException() {
        super();
    }
    public SurnameIllegalException(String mensaje) {
        super(mensaje);
    }
}
