package org.example.next_goat.Exceptios;

public class NameIllegalException extends Exception{
    public NameIllegalException() {
        super();
    }
    public NameIllegalException(String mensaje) {
        super(mensaje);
    }
}
