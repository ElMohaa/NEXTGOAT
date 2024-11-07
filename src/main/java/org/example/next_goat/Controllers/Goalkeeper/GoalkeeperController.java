package org.example.next_goat.Controllers.Goalkeeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GoalkeeperController {

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
        changeScene(event, "/FXML/Training.fxml");
    }

    @FXML
    private void bootonSoot(ActionEvent event) {
        changeScene(event, "/FXML/Doorman/Soot/Soot.fxml");
    }

    @FXML
    private void bootonPass(ActionEvent event) {
        changeScene(event, "/FXML/Doorman/PassGK/PassGK.fxml");
    }

    @FXML
    private void bootonControl(ActionEvent event) {
        changeScene(event, "/FXML/Doorman/Control/ControlGK.fxml");
    }
}
