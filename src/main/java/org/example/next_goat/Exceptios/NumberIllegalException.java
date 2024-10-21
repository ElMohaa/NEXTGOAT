package org.example.next_goat.Exceptios;

public class NumberIllegalException extends Exception{
    public NumberIllegalException() {
        super();
    }
    public NumberIllegalException(String mensaje) {
        super(mensaje);
    }
}
