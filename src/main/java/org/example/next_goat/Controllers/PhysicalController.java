package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PhysicalController {

    // Método genérico para cargar y cambiar la escena
    private void changeScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();

            Scene newScene = new Scene(newView);

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
    private void buttonForce(ActionEvent event) {
        changeScene(event, "/FXML/Physical/Force.fxml");
    }

    @FXML
    private void buttonSpeed(ActionEvent event) {
        changeScene(event, "/FXML/Physical/Speed.fxml");
    }

    @FXML
    private void buttonResis(ActionEvent event) {
        changeScene(event, "/FXML/Physical/Resistance.fxml");
    }

    @FXML
    private void buttonMobby(ActionEvent event) {
        changeScene(event, "/FXML/Physical/Mobility.fxml");
    }
}
