package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.next_goat.Clases.UserSession;
import org.example.next_goat.Clases.Usuario;
import org.example.next_goat.DataBase.DataBaseConnection;
import org.example.next_goat.Exceptios.*;

import java.io.IOException;
import java.sql.Date;

public class EditUserController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField dniField;
    @FXML
    private TextField fechaField;


    private Usuario currentUser;

    // Este método se llama al cargar la ventana de edición
    public void initialize() {
        loadUserInfo();
    }

    // Método para cargar la información actual del usuario desde la base de datos
    private void loadUserInfo() {
        int userId = UserSession.getInstance().getIdUsuario(); // Obtener el id del usuario desde la sesión
        currentUser = DataBaseConnection.getUserById(userId); // Obtener la información del usuario desde la base de datos

        if (currentUser != null) {
            // Rellenar los campos con la información actual
            nameField.setText(currentUser.getNombre_usuario());
            surnameField.setText(currentUser.getApellidos_usuario());
            emailField.setText(currentUser.getCorreo_usuario());
            phoneField.setText(currentUser.getTelefono_usuario());
            addressField.setText(currentUser.getDirrecion_vivienda());
            usernameField.setText(currentUser.getUsername());
            passwordField.setText(currentUser.getContrsena());
            dniField.setText(currentUser.getDni_usuario());
            fechaField.setText(String.valueOf((currentUser.getFecha_nacimiento())));
        }
    }

    // Método para guardar los cambios en la base de datos
    @FXML
    private void saveUser(ActionEvent event)  {
        try {
            // Obtener los valores de los TextField
            String name = nameField.getText();
            String surname = surnameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String dni = dniField.getText();
            Date fechaNac = Date.valueOf(fechaField.getText());

            // Actualizar la información del usuario en la base de datos
            currentUser.setNombre_usuario(name);
            currentUser.setApellidos_usuario(surname);
            currentUser.setCorreo_usuario(email);
            currentUser.setTelefono_usuario(phone);
            currentUser.setDirrecion_vivienda(address);
            currentUser.setUsername(username);
            currentUser.setContrsena(password);
            currentUser.setDni_usuario(dni);
            currentUser.setFecha_nacimiento(fechaNac);

            // Actualizar los datos en la base de datos
            DataBaseConnection.updateUser(currentUser);

            // Mostrar un mensaje de confirmación
            showAlert("Éxito", "La información se ha actualizado correctamente.");

            // Volver a la pantalla anterior
            back(event);
        } catch (DNIIllegalException d){
            showAlert("Error de Registro","El DNI o NIE es incorrecto");
        }catch (NullPointerException n){
            showAlert("Error de Registro","Hay un campo vacio, deben estar todos los campos completados");
        }catch (SurnameIllegalException s){
            showAlert("Error de Registro","El apellido esta vacio o/y solo debe contener letras");
        }catch (NameIllegalException na){
            showAlert("Error de Registro","El nombre esta vacio o/y solo debe contener letras");
        }catch (NumberIllegalException es){
            showAlert("Error de Registro","El numero es demasiado corto o largo, recuerda son 9 digitos");
        }catch(EmailIllegalException ma ){
            showAlert("Error de Registro","El email no tiene el formato correcto");
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para volver a la pantalla anterior
    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/User.fxml"));
            Parent traView = loader.load();
            Scene laLigaScene = new Scene(traView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
