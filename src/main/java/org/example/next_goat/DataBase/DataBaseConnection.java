package org.example.next_goat.DataBase;

import org.example.next_goat.Clases.MejoraFisica;
import org.example.next_goat.Clases.Usuario;
import org.example.next_goat.Exceptios.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String sql = "INSERT INTO Usuario (nombre_usuario, apellidos_usuario, dni, fecha_nacimiento, correo_usuario, telefono_usuario, dirrecion_vivienda, username, contrsena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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


            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long idUsuario = generatedKeys.getLong(1);
                    // Ahora insertamos un registro en la tabla MejoraFisica
                    String sqlMejoraFisica = "INSERT INTO MejoraFisica (id_usuario) VALUES (?)";
                    try (PreparedStatement pstmtMejora = conn.prepareStatement(sqlMejoraFisica)) {
                        pstmtMejora.setLong(1, idUsuario);
                        pstmtMejora.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static boolean checkEmailExists(Usuario usuario) {
        String sql = "SELECT * FROM Usuario WHERE correo_usuario = ? and id_usuario != ?";
        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getCorreo_usuario());
            pstmt.setString(2, String.valueOf(usuario.getId_usuario()));


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
    public static boolean checkUserExists(Usuario usuario) {
        String sql = "SELECT * FROM Usuario WHERE  username = ? and id_usuario != ?";
        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, String.valueOf(usuario.getId_usuario()));

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
    public static boolean checkTelExists(Usuario usuario) {
        String sql = "SELECT * FROM Usuario WHERE  telefono_usuario = ?and id_usuario != ?";
        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getTelefono_usuario());
            pstmt.setString(2, String.valueOf(usuario.getId_usuario()));

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
        String fullName = null;


        try (Connection connection = DriverManager.getConnection(urlDB, user, password)) {

            String sql = "SELECT nombre_usuario FROM Usuario WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username); // Establece el nombre de usuario en la consulta

            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                fullName = resultSet.getString("nombre_usuario");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fullName; // Devuelve el nombre completo
    }

    public static boolean authenticateUser(String username, String passwordd) {
        String sql = "SELECT * FROM Usuario WHERE username = ? AND contrsena = ?";

        try (Connection conn = DriverManager.getConnection(urlDB, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordd);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();
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
        return null;
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
                    return rs.getInt("id_usuario");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el correo por username: " + e.getMessage());
        }
        return 0;
    }

    public static String getFullNameById(int id) {
        String fullName = null; // Variable para almacenar el nombre completo


        try (Connection connection = DriverManager.getConnection(urlDB, user, password)) {

            String sql = "SELECT nombre_usuario FROM Usuario WHERE id_usuario = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Si se encuentra un resultado, obtén el nombre completo
            if (resultSet.next()) {
                fullName = resultSet.getString("nombre_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullName;
    }
    public static void updateUser(Usuario usuario) {
        String query = "UPDATE Usuario SET nombre_usuario = ?, apellidos_usuario = ?,dni = ?,fecha_nacimiento=?, correo_usuario = ?, telefono_usuario = ?, dirrecion_vivienda = ?, username = ?, contrsena = ? WHERE id_usuario = ?";

        try (Connection conn = DriverManager.getConnection(urlDB,user,password); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre_usuario());
            stmt.setString(2, usuario.getApellidos_usuario());
            stmt.setString(3,usuario.getDni_usuario());
            stmt.setDate(4, (Date) usuario.getFecha_nacimiento());
            stmt.setString(5, usuario.getCorreo_usuario());
            stmt.setString(6, usuario.getTelefono_usuario());
            stmt.setString(7, usuario.getDirrecion_vivienda());
            stmt.setString(8, usuario.getUsername());
            stmt.setString(9, usuario.getContrsena());
            stmt.setInt(10, usuario.getId_usuario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Usuario getUserById(int userId) {
        Usuario usuario = null;
        String query = "SELECT * FROM Usuario WHERE id_usuario = ?";

        try (Connection conn = DriverManager.getConnection(urlDB,user,password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establecer el valor del id_usuario en la consulta
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                // Si se encuentra el usuario
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    usuario.setApellidos_usuario(rs.getString("apellidos_usuario"));
                    usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                    usuario.setCorreo_usuario(rs.getString("correo_usuario"));
                    usuario.setTelefono_usuario(rs.getString("telefono_usuario"));
                    usuario.setDirrecion_vivienda(rs.getString("dirrecion_vivienda"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setContrsena(rs.getString("contrsena"));
                    usuario.setDni_usuario(rs.getString("dni"));
                }
            } catch (SurnameIllegalException e) {
                throw new RuntimeException(e);
            } catch (NumberIllegalException e) {
                throw new RuntimeException(e);
            } catch (NameIllegalException e) {
                throw new RuntimeException(e);
            } catch (EmailIllegalException e) {
                throw new RuntimeException(e);
            } catch (DNIIllegalException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario; // Retorna el usuario encontrado o null si no se encuentra
    }

    public static Map<String, List<String>> getRutinasPorPosicion(String posicion) {
        Map<String, List<String>> rutinasSemanales = new HashMap<>();
        String sql = "SELECT r.dia_semana, r.id_rutina, a.descripcion " +
                "FROM Rutina r " +
                "JOIN Actividad a ON r.id_rutina = a.id_rutina " +
                "WHERE r.posicion = ? " +
                "ORDER BY FIELD(r.dia_semana, 'LUNES', 'MARTES', 'MIÉRCOLES', 'JUEVES', 'VIERNES')";

        try (Connection conn = DriverManager.getConnection(getUrlDB(), getUser(), getPassword());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, posicion);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String dia = rs.getString("dia_semana").toUpperCase();
                    String idRutina = rs.getString("id_rutina"); // Obtener el id_rutina

                    // Agregar la rutina al día correspondiente
                    rutinasSemanales.computeIfAbsent(dia, k -> new ArrayList<>()).add(idRutina);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rutinasSemanales;
    }


    public static List<String> getActividadesPorRutina(String rutina) {
        List<String> actividades = new ArrayList<>();
        String sql = "SELECT duracion, titulo, descripcion FROM Actividad WHERE id_rutina = ? ORDER BY orden";

        try (Connection conn = DriverManager.getConnection(getUrlDB(), getUser(), getPassword());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, rutina);  // Se pasa el id_rutina correspondiente

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String duracion = rs.getString("duracion");
                    String titulo = rs.getString("titulo");
                    String descripcion = rs.getString("descripcion");

                    // Concatenar la actividad completa para enviarla al controlador
                    actividades.add(titulo + " - " + duracion + " - " + descripcion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividades;
    }





}