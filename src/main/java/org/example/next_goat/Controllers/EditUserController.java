package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        int userId = UserSession.getInstance().getIdUsuario();
        currentUser = DataBaseConnection.getUserById(userId);

        if (currentUser != null) {

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

            // Verificar si el usuario ya existe en la base de datos
            if (DataBaseConnection.checkUserExists(currentUser)) {
                // Mostrar un mensaje de error si el usuario ya existe
                showWindowError("An account with that username already exists");;
                return; // Salir de la función sin intentar la inserción
            } else if (DataBaseConnection.checkEmailExists(currentUser)) {
                showWindowError("An account with that email already exists.");
                return;
            } else if (DataBaseConnection.checkTelExists(currentUser)) {
                showWindowError("An account with that phone number already exists.");
                return;
            }


            DataBaseConnection.updateUser(currentUser);

            showWindowGood( "The information has been updated successfully");


        } catch (DNIIllegalException d){
            showWindowError("The DNI or NIE is incorrect.");

        }catch (NullPointerException n){
            showWindowError("There is an empty field, all fields must be completed.");

        }catch (SurnameIllegalException s){
            showWindowError("The last name is empty and/or it should only contain letters.");

        }catch (NameIllegalException na){
            showWindowError("The first name is empty and/or it should only contain letters.");

        }catch (NumberIllegalException es){
            showWindowError("The number is too short or too long, remember it must be 9 digits");

        }catch(EmailIllegalException ma ){
            showWindowError("The email does not have the correct format");

        } catch (Exception e) {
            System.err.println("Error al registrar al usuario: " + e.getMessage());
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

    public void showWindowError(String text){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/error.fxml"));
            Parent dialogParent = loader.load();
            Scene dialogScene = new Scene(dialogParent);

            ErrorController puwc=loader.getController();
            puwc.setErrorText(text);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(dialogScene);
            stage.show();
        } catch (Exception ex) {
            System.err.println("Error: "+ ex.getMessage());

        }
    }

    public void showWindowGood(String text){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/good.fxml"));
            Parent dialogParent = loader.load();
            Scene dialogScene = new Scene(dialogParent);

            ErrorController puwc=loader.getController();
            puwc.setErrorText(text);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(dialogScene);
            stage.show();
        } catch (Exception ex) {
            System.err.println("Error: "+ ex.getMessage());

        }
    }
}