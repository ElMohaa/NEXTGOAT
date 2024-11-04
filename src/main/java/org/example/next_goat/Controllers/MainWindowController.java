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
    private void handleLaLigaButtonAction(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/LaLigaView.fxml"));
            Parent laLigaView = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(laLigaView);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleEntreButtonAction(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Training.fxml"));
            Parent traView = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(traView);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonAtletismo(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Physical.fxml"));
            Parent traView = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(traView);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonRecovery(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Recovery.fxml"));
            Parent traView = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(traView);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonUser(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/User.fxml"));
            Parent traView = loader.load();

            // Crear una nueva escena con la ventana de LaLiga
            Scene laLigaScene = new Scene(traView);

            // Obtener el stage actual y cambiar la escena
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
