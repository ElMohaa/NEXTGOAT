package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private Label welcomeLabelStart;

    @FXML
    private Button continueButtonStart;

    // Este método se llamará para inicializar la pantalla con el nombre del usuario.
    public void initialize(String username) {
        welcomeLabelStart.setText("Welcome, " + username);
    }
    public void setUsername(String username) {
        welcomeLabelStart.setText("Welcome, " + username);
    }

    @FXML
    private void handleContinueButton(ActionEvent event) {
        try {
            // Aquí puedes cargar tu ventana principal, por ejemplo main.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainWindow.fxml"));
            Parent root = loader.load();

            // Obtiene el controlador de UserController
            UserController userController = (UserController) ((Node) event.getSource()).getScene().getWindow()
                    .getUserData();  // Recuperamos el UserController que se pasó previamente

            // Recupera el controlador de TrainingController
            MainWindowController trainingController = loader.getController();

            // Le pasamos el controlador de UserController a TrainingController
            trainingController.setUserController(userController);

            Stage stage = (Stage) continueButtonStart.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show(); // Asegúrate de mostrar la nueva escena
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
