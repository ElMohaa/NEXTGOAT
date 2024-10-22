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
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainWindow.fxml"));
            Parent training = loader.load();

            Scene trainingScene = new Scene(training);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(trainingScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDelanteroButtonAction(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Forward.fxml"));
            Parent Forward = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(Forward);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleCentroButtonAction(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Midfielder.fxml"));
            Parent medio = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(medio);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDefensaButtonAction(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Defense.fxml"));
            Parent defense = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(defense);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handlePorteroButtonAction(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Doorman.fxml"));
            Parent Doorman = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(Doorman);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
