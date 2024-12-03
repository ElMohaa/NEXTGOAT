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
import javafx.stage.StageStyle;
import org.example.next_goat.Clases.UserSession;
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
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    // Método para iniciar sesión
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (DataBaseConnection.authenticateUser(username, password)) {
            int userId = DataBaseConnection.getIdByUsername(username);

            // Guardar el id_usuario en la sesión
            UserSession.getInstance().setIdUsuario(userId);
            // Si la autenticación es exitosa, carga la ventana Start.fxml
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Start.fxml"));
                Parent startPage = loader.load();


                String nombre=DataBaseConnection.getFullNameByUsername(username);
                StartController startController = loader.getController();
                startController.setUsername(nombre);

                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene startScene = new Scene(startPage);
                stage.setScene(startScene);
                stage.setTitle("Inicio");
            } catch (Exception e) {
                System.err.println("Error al cargar la ventana de inicio: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            showWindowError( "Incorrect username or password.");
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

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
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
}
