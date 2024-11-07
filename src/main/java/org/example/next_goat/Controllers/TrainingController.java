package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TrainingController {

    // Método genérico para cargar y cambiar la escena
    private void changeScene(ActionEvent event, String fxmlPath) {
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
    private void handleBackButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/MainWindow.fxml");
    }

    @FXML
    private void handleDelanteroButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/ForwardAndMid/Forward.fxml");
    }

    @FXML
    private void handleCentroButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/ForwardAndMid/Midfielder.fxml");
    }

    @FXML
    private void handleDefensaButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/Defense/Defense.fxml");
    }

    @FXML
    private void handlePorteroButtonAction(ActionEvent event) {
        changeScene(event, "/FXML/Doorman/Goalkeeper.fxml");
    }
}
