package org.example.next_goat.DataBase;

import org.example.next_goat.Clases.MejoraFisica;
import org.example.next_goat.Clases.Usuario;

import java.sql.*;

public class DataBaseConnection {
    private static String urlDB = "jdbc:mysql://192.168.56.101:3306/nextgoat";
    private static String user = "admin00";
    private static String password = "alumno";

    public static void setUrlDB(String urlDB) {
        DataBaseConnection.urlDB = urlDB;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        DataBaseConnection.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataBaseConnection.password = password;
    }

    public static  String getUrlDB(){return urlDB;}


    public DataBaseConnection(){}


    public static void insertUser(Usuario usuario) {
        String urlDB = getUrlDB();
        String user = getUser();
        String password = getPassword();

        String sql = "INSERT INTO Usuario (nombre_usuario, apellidos_usuario, dni, fecha_nacimiento, correo_usuario, telefono_usuario, dirrecion_vivienda, username, contrsena, edad_ususario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Insertamos el usuario
            pstmt.setString(1, usuario.getNombre_usuario());
            pstmt.setString(2, usuario.getApellidos_usuario());
            pstmt.setString(3, usuario.getDni_usuario());
            pstmt.setDate(4, new java.sql.Date(usuario.getFecha_nacimiento().getTime()));
            pstmt.setString(5, usuario.getCorreo_usuario());
            pstmt.setString(6, usuario.getTelefono_usuario());
            pstmt.setString(7, usuario.getDirrecion_vivienda());
            pstmt.setString(8, usuario.getUsername());
            pstmt.setString(9, usuario.getContrsena());
            pstmt.setInt(10, usuario.getEdad_ususario());

            pstmt.executeUpdate();

            // Obtener el id_usuario generado automáticamente
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long idUsuario = generatedKeys.getLong(1);  // El id del nuevo usuario insertado
                    // Ahora insertamos un registro en la tabla MejoraFisica
                    String sqlMejoraFisica = "INSERT INTO MejoraFisica (id_usuario) VALUES (?)";
                    try (PreparedStatement pstmtMejora = conn.prepareStatement(sqlMejoraFisica)) {
                        pstmtMejora.setLong(1, idUsuario);
                        pstmtMejora.executeUpdate();  // Inserta el registro de mejora física para el nuevo usuario
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static boolean checkUserExists(Usuario usuario) {
        String sql = "SELECT * FROM Usuario WHERE correo_usuario = ? OR username = ? OR telefono_usuario = ?";
        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getCorreo_usuario());
            pstmt.setString(2, usuario.getUsername());
            pstmt.setString(3, usuario.getTelefono_usuario());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static String getFullNameByUsername(String username) {
        String fullName = null; // Variable para almacenar el nombre completo

        // Intenta conectarse a la base de datos y ejecutar la consulta
        try (Connection connection = DriverManager.getConnection(urlDB, user, password)) {
            // Consulta SQL para obtener el nombre completo
            String sql = "SELECT nombre_usuario FROM Usuario WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username); // Establece el nombre de usuario en la consulta

            ResultSet resultSet = preparedStatement.executeQuery();

            // Si se encuentra un resultado, obtén el nombre completo
            if (resultSet.next()) {
                fullName = resultSet.getString("nombre_usuario");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        return fullName; // Retorna el nombre completo (o null si no se encuentra)
    }

    public static boolean authenticateUser(String username, String passwordd) {
        String sql = "SELECT * FROM Usuario WHERE username = ? AND contrsena = ?";

        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordd);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // Si hay un resultado, el usuario existe y la contraseña es correcta
        } catch (SQLException e) {
            System.out.println("Error en la autenticación: " + e.getMessage());
            return false;
        }
    }


    public static String getEmailByUsername(String username) {
        String sql = "SELECT correo_usuario FROM Usuario WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("correo_usuario"); // Devuelve el correo electrónico asociado
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el correo por username: " + e.getMessage());
        }
        return null; // Retorna null si no se encuentra el usuario
    }
    public static boolean updateUserPasswordByUsername(String username, String newPassword) {
        String sql = "UPDATE Usuario SET contrsena = ? WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Si se afectaron filas, la actualización fue exitosa
        } catch (SQLException e) {
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
        }
        return false;
    }

    public static MejoraFisica getMejoraFisicaByUserId(int idUsuario) {
        String sql = "SELECT * FROM MejoraFisica WHERE id_usuario = ?";
        MejoraFisica mejoraFisica = null;

        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    mejoraFisica = new MejoraFisica(
                            rs.getInt("id_usuario"),
                            rs.getInt("ritmo"),
                            rs.getInt("regate"),
                            rs.getInt("fisico"),
                            rs.getInt("tiro"),
                            rs.getInt("pase"),
                            rs.getInt("defensa"),
                            rs.getInt("media")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mejoraFisica;
    }
    public static boolean updateMejoraFisica(MejoraFisica mejoraFisica) {
        String sql = "UPDATE MejoraFisica SET ritmo = ?, regate = ?, fisico = ?, tiro = ?, pase = ?, defensa = ?, media = ? WHERE id_usuario = ?";

        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, mejoraFisica.getRitmo());
            pstmt.setInt(2, mejoraFisica.getRegate());
            pstmt.setInt(3, mejoraFisica.getFisico());
            pstmt.setInt(4, mejoraFisica.getTiro());
            pstmt.setInt(5, mejoraFisica.getPase());
            pstmt.setInt(6, mejoraFisica.getDefensa());
            pstmt.setInt(7, mejoraFisica.getMedia());
            pstmt.setInt(8, mejoraFisica.getId_usuario());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static int getIdByUsername(String username) {
        String sql = "SELECT id_usuario FROM Usuario WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_usuario"); // Devuelve el correo electrónico asociado
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el correo por username: " + e.getMessage());
        }
        return 0; // Retorna null si no se encuentra el usuario
    }

    public static String getFullNameById(int id) {
        String fullName = null; // Variable para almacenar el nombre completo

        // Intenta conectarse a la base de datos y ejecutar la consulta
        try (Connection connection = DriverManager.getConnection(urlDB, user, password)) {
            // Consulta SQL para obtener el nombre completo
            String sql = "SELECT nombre_usuario FROM Usuario WHERE id_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id); // Establece el nombre de usuario en la consulta

            ResultSet resultSet = preparedStatement.executeQuery();

            // Si se encuentra un resultado, obtén el nombre completo
            if (resultSet.next()) {
                fullName = resultSet.getString("nombre_usuario");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        return fullName; // Retorna el nombre completo (o null si no se encuentra)
    }






}
