package org.example.next_goat.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button continueButton;

    // Este método se llamará para inicializar la pantalla con el nombre del usuario.
    public void initialize(String username) {
        welcomeLabel.setText("Welcome, " + username);
    }
    public void setUsername(String username) {
        welcomeLabel.setText("Welcome, " + username);
    }

    @FXML
    private void handleContinueButton() {
        try {
            // Aquí puedes cargar tu ventana principal, por ejemplo main.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainWindow.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show(); // Asegúrate de mostrar la nueva escena
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
