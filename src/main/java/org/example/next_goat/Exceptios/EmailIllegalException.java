package org.example.next_goat.Exceptios;

public class EmailIllegalException extends Exception{
    public EmailIllegalException() {
        super();
    }
    public EmailIllegalException(String mensaje) {
        super(mensaje);
    }
}
