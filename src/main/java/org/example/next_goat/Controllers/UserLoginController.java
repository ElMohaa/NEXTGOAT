package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.example.next_goat.DataBase.DataBaseConnection;

import java.io.IOException;

public class UserLoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;
    @FXML
    private Button resset;

    @FXML
    private TextField usernameField; // Agrega este campo

    @FXML
    private TextField passwordField; // Agrega este campo

    // Método para iniciar sesión
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (DataBaseConnection.authenticateUser(username, password)) {
            // Si la autenticación es exitosa, carga la ventana Start.fxml
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Start.fxml"));
                Parent startPage = loader.load();

                // Obtener el controlador de Start.fxml
                String nombre=DataBaseConnection.getFullNameByUsername(username);
                StartController startController = loader.getController();
                startController.setUsername(nombre); // Pasar el nombre de usuario

                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene startScene = new Scene(startPage);
                stage.setScene(startScene);
                stage.setTitle("Inicio"); // Cambia el título a "Inicio"
            } catch (Exception e) {
                System.err.println("Error al cargar la ventana de inicio: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            // Si la autenticación falla, muestra un mensaje de error
            showAlert("Error", "Nombre de usuario o contraseña incorrectos.");
        }
    }


    // Navegar a la ventana de Sign Up
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UserSuscribe.fxml"));
            Parent signUpPage = loader.load();

            Stage stage = (Stage) signUpButton.getScene().getWindow();
            stage.setScene(new Scene(signUpPage, 315, 615));
            stage.setTitle("Sign Up");
        } catch (Exception e) {
            System.err.println("Error al cargar la ventana de registro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void handleForgotPasswordAction() {
        try {
            // Cargar la nueva ventana de restablecer contraseña
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ResetPassword.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual desde el botón (u otro nodo de la ventana actual)
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root)); // Cambia la escena a la nueva ventana
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Método para mostrar alertas
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
