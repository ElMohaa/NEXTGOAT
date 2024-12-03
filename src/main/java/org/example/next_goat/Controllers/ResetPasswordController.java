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
import org.example.next_goat.Email.EmailSender;
import org.example.next_goat.DataBase.DataBaseConnection;
import org.example.next_goat.Utilities.VerificationCodeGenerator;

import java.io.IOException;

public class ResetPasswordController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField verificationCodeField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Button sendCodeButton;
    @FXML
    private Button verifyCodeButton;
    @FXML
    private Button changePasswordButton;

    private String generatedCode;
    private String userEmail;

    @FXML
    private void handleSendCodeAction(ActionEvent event) {
        String username = usernameField.getText();

        // Obtener el correo electrónico a partir del username
        userEmail = DataBaseConnection.getEmailByUsername(username);

        if (userEmail == null) {
            showWindowError( "No user was found with that username.");
            return;
        }

        // Generar el código de verificación
        generatedCode = VerificationCodeGenerator.generateCode();

        // Enviar el código por correo
        EmailSender.sendVerificationCode(userEmail, generatedCode);

        showWindowGood("The verification code has been sent to your email.");
    }

    @FXML
    private void handleVerifyCodeAction(ActionEvent event) {
        String enteredCode = verificationCodeField.getText();

        if (generatedCode != null && enteredCode.equals(generatedCode)) {
            showWindowGood( "The code is correct. You can now change your password.");
            changePasswordButton.setDisable(false); // Habilitar el botón para cambiar la contraseña
        } else {
            showWindowError("The code is incorrect");
        }
    }

    @FXML
    private void handleChangePasswordAction(ActionEvent event) {
        String username = usernameField.getText();
        String newPassword = newPasswordField.getText();

        if (newPassword.isEmpty()) {
            showWindowError("Enter the new password.");
            return;
        }

        // Actualizar la contraseña en la base de datos
        boolean passwordUpdated = DataBaseConnection.updateUserPasswordByUsername(username, newPassword);

        if (passwordUpdated) {
            showWindowGood( "Password updated successfully");
            // Redirigir a la pantalla de inicio de sesión si es necesario
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UserLogin.fxml"));
                Parent loginRoot = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene loginScene = new Scene(loginRoot);
                stage.setScene(loginScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showWindowError("Could not load the login window.");
            }
        } else {
            showWindowError( "There was a problem updating the password.");
        }
    }
    @FXML
    private void handleCancelAction(ActionEvent event) {
        try {
            Parent loginPage = FXMLLoader.load(getClass().getResource("/FXML/UserLogin.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(loginPage, 315, 615));
            stage.setTitle("Login");
        } catch (Exception e) {
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
