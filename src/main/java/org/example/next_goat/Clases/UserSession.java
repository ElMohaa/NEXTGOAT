package org.example.next_goat.Clases;

public class UserSession {
    private static UserSession instance;
    private int idUsuario;
    private String nombreUsuario;


    private UserSession() {}


    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public void clearSession() {
        this.idUsuario = -1;
        this.nombreUsuario = null;
    }
}
