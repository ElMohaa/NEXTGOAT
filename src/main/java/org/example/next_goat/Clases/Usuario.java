package org.example.next_goat.Clases;

import org.example.next_goat.Exceptios.*;

import java.time.LocalDate;
import java.util.Date;

public class Usuario {
    int id_usuario;
    String nombre_usuario;
    String apellidos_usuario;
    Date fecha_nacimiento;
    String correo_usuario;
    String telefono_usuario;
    String dirrecion_vivienda;
    String username;
    String contrsena;
    String dni_usuario;

    public Usuario(){}

    public Usuario(int id_usuario, String nombre_usuario, String apellidos_usuario, Date fecha_nacimiento, String correo_usuario, String telefono_usuario,String dirrecion_vivienda, String username, String contrsena,String dni_usuario) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellidos_usuario = apellidos_usuario;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo_usuario = correo_usuario;
        this.telefono_usuario = telefono_usuario;
        this.dirrecion_vivienda=dirrecion_vivienda;
        this.username = username;
        this.contrsena = contrsena;
        this.dni_usuario=dni_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) throws NameIllegalException {
        if (nombre_usuario.matches("[a-zA-Z]+")) {
            // Convertimos la primera letra a mayúscula y el resto a minúscula.
            String nombreFormateado = nombre_usuario.substring(0, 1).toUpperCase() +
                    nombre_usuario.substring(1).toLowerCase();
            this.nombre_usuario = nombreFormateado;
        } else {
            throw new NameIllegalException("El nombre solo debe contener letras");
        }
    }


    public String getApellidos_usuario() {
        return apellidos_usuario;
    }

    public void setApellidos_usuario(String apellidos_usuario) throws SurnameIllegalException {
        if (apellidos_usuario.matches("[a-zA-Z\\s]+")) {
            // Convertimos cada palabra del apellido a la forma "PrimeraLetraMayúscula".
            String apellidosFormateados = formatApellidos(apellidos_usuario);
            this.apellidos_usuario = apellidosFormateados;
        } else {
            throw new SurnameIllegalException("El apellido no puede contener números ni caracteres especiales");
        }
    }

    // Método auxiliar para formatear los apellidos.
    private String formatApellidos(String apellidos) {
        String[] palabras = apellidos.split("\\s+"); // Dividimos por espacios.
        StringBuilder apellidosFormateados = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                // Formateamos cada palabra: primera letra mayúscula y el resto minúsculas.
                apellidosFormateados.append(palabra.substring(0, 1).toUpperCase())
                        .append(palabra.substring(1).toLowerCase())
                        .append(" "); // Añadimos un espacio después de cada palabra.
            }
        }

        // Eliminamos el último espacio adicional y devolvemos el resultado.
        return apellidosFormateados.toString().trim();
    }


    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimiento = new java.sql.Date(fecha_nacimiento.getTime()).toLocalDate();

        if (fechaNacimiento.isAfter(fechaActual)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }

        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) throws EmailIllegalException {
        String regla="^(.+)@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (correo_usuario.matches(regla)){
            this.correo_usuario=correo_usuario;
        }else {
            throw new EmailIllegalException("El correo no tiene un formato correcto");
        }
    }

    public String getTelefono_usuario() {
        return telefono_usuario;
    }

    public void setTelefono_usuario(String telefono_usuario) throws NumberIllegalException {
        if (telefono_usuario.length()==9){
            this.telefono_usuario = telefono_usuario;
        }else {
            throw new NumberIllegalException("El numero es demasiado corto o largo, recuerda son nuve digitos");
        }
    }

    public String getDirrecion_vivienda() {
        return dirrecion_vivienda;
    }

    public void setDirrecion_vivienda(String dirrecion_vivienda) {
        if (dirrecion_vivienda==null){
            throw new NullPointerException("La direccion no puede estar vacia");
        }else {
            this.dirrecion_vivienda = dirrecion_vivienda;
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username==null){
            throw new NullPointerException("El campo username tiene que ser llenado");
        }else {
            this.username = username;
        }
    }

    public String getContrsena() {
        return contrsena;
    }

    public void setContrsena(String contrsena) {
        if (contrsena==null){
            throw new NullPointerException("La contraseña se debe de llenar");
        }else{
            this.contrsena = contrsena;
        }
    }



    public String getDni_usuario() {
        return dni_usuario;
    }

    public void setDni_usuario(String dni_usuario) throws DNIIllegalException {
        String dni = "^\\d{8}[A-Za-z]$";
        String nie = "^[A-Za-z]\\d{7}[A-Za-z]$";

        if (dni_usuario.matches(dni)||dni_usuario.matches(nie)) {
            this.dni_usuario = dni_usuario.toUpperCase();
        } else {
            throw new DNIIllegalException("El DNI tiene un formato inválido.");
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", apellidos_usuario='" + apellidos_usuario + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", correo_usuario='" + correo_usuario + '\'' +
                ", telefono_usuario='" + telefono_usuario + '\'' +
                ", dirrecion_vivienda='" + dirrecion_vivienda + '\'' +
                ", username='" + username + '\'' +
                ", contrsena='" + contrsena + '\'' +
                ", dni_usuario='" + dni_usuario + '\'' +
                '}';
    }
}
