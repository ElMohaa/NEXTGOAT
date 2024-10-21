package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.next_goat.Email.EmailSender;
import org.example.next_goat.DataBase.DataBaseConnection;
import org.example.next_goat.Utilities.VerificationCodeGenerator;

import java.io.IOException;

public class ResetPasswordController {

    @FXML
    private TextField usernameField; // Campo para ingresar el nombre de usuario
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

    private String generatedCode; // Almacena el código generado
    private String userEmail; // Almacena el correo del usuario

    @FXML
    private void handleSendCodeAction(ActionEvent event) {
        String username = usernameField.getText();

        // Obtener el correo electrónico a partir del username
        userEmail = DataBaseConnection.getEmailByUsername(username);

        if (userEmail == null) {
            showStyledAlert("Error", "No se encontró ningún usuario con ese nombre de usuario.");
            return;
        }

        // Generar el código de verificación
        generatedCode = VerificationCodeGenerator.generateCode();

        // Enviar el código por correo
        EmailSender.sendVerificationCode(userEmail, generatedCode);

        showStyledAlert("Código Enviado", "El código de verificación ha sido enviado a tu correo.");
    }

    @FXML
    private void handleVerifyCodeAction(ActionEvent event) {
        String enteredCode = verificationCodeField.getText();

        if (generatedCode != null && enteredCode.equals(generatedCode)) {
            showStyledAlert("Éxito", "El código es correcto. Ahora puedes cambiar tu contraseña.");
            changePasswordButton.setDisable(false); // Habilitar el botón para cambiar la contraseña
        } else {
            showStyledAlert("Error", "El código ingresado es incorrecto.");
        }
    }

    @FXML
    private void handleChangePasswordAction(ActionEvent event) {
        String username = usernameField.getText();
        String newPassword = newPasswordField.getText();

        if (newPassword.isEmpty()) {
            showStyledAlert("Error", "La nueva contraseña no puede estar vacía.");
            return;
        }

        // Actualizar la contraseña en la base de datos
        boolean passwordUpdated = DataBaseConnection.updateUserPasswordByUsername(username, newPassword);

        if (passwordUpdated) {
            showStyledAlert("Éxito", "Contraseña actualizada correctamente.");
            // Redirigir a la pantalla de inicio de sesión si es necesario
            try {
                // Cargar la vista del login
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UserLogin.fxml"));
                Parent loginRoot = loader.load();

                // Obtener el stage actual a partir del evento
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Configurar la nueva escena de login
                Scene loginScene = new Scene(loginRoot);
                stage.setScene(loginScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showStyledAlert("Error", "No se pudo cargar la ventana de inicio de sesión.");
            }
        } else {
            showStyledAlert("Error", "Hubo un problema al actualizar la contraseña.");
        }
    }
    @FXML
    private void handleCancelAction(ActionEvent event) {
        try {
            // Cargar la pantalla de Login
            Parent loginPage = FXMLLoader.load(getClass().getResource("/FXML/UserLogin.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            // Cambiar la escena a la pantalla de login
            stage.setScene(new Scene(loginPage, 315, 615));
            stage.setTitle("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showStyledAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
