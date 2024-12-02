package org.example.next_goat.Controllers.ForAndMid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ForwardController {

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
        changeScene(event, "/FXML/Training.fxml");
    }

    @FXML
    private void controlball(ActionEvent event) {
        changeScene(event, "/FXML/ForwardAndMid/DRIBLLES/BallControl.fxml");
    }

    @FXML
    private void topass(ActionEvent event) {
        changeScene(event, "/FXML/ForwardAndMid/TOPASS/ToPass.fxml");
    }

    @FXML
    private void control(ActionEvent event) {
        changeScene(event, "/FXML/ForwardAndMid/COTROLLBALL/ControllBall.fxml");
    }

    @FXML
    private void skill(ActionEvent event) {
        changeScene(event, "/FXML/ForwardAndMid/SKILL/Habilidad.fxml");
    }
}
