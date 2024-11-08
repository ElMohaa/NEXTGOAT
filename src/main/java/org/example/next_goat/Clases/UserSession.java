package org.example.next_goat.Clases;

public class UserSession {
    private static UserSession instance;
    private int idUsuario;
    private String nombreUsuario;

    // Constructor privado para evitar la creación de instancias
    private UserSession() {}

    // Método para obtener la instancia única (Singleton)
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Métodos para acceder y modificar el id_usuario y otros datos del usuario
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

    // Limpiar la sesión (cuando el usuario cierre sesión, por ejemplo)
    public void clearSession() {
        this.idUsuario = -1; // O cualquier valor que indique que no hay usuario logueado
        this.nombreUsuario = null;
    }
}
