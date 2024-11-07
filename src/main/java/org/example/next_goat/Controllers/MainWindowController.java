package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private void handleButtonAction(ActionEvent event, String fxmlPath) {
        try {
            // Cargar la vista desde el archivo FXML especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();

            // Crear una nueva escena con la vista cargada
            Scene newScene = new Scene(newView);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLaLigaButtonAction(ActionEvent event) {
        handleButtonAction(event, "/FXML/LaLigaView.fxml");
    }

    @FXML
    private void handleEntreButtonAction(ActionEvent event) {
        handleButtonAction(event, "/FXML/Training.fxml");
    }

    @FXML
    private void buttonAtletismo(ActionEvent event) {
        handleButtonAction(event, "/FXML/Physical.fxml");
    }

    @FXML
    private void buttonRecovery(ActionEvent event) {
        handleButtonAction(event, "/FXML/Recovery.fxml");
    }

    @FXML
    private void buttonUser(ActionEvent event) {
        handleButtonAction(event, "/FXML/User.fxml");
    }
}
