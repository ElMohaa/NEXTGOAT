package org.example.next_goat.Controllers.Skill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BallControll {

    @FXML
    private void buttonCochac1(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DRIBLLES/Coach1BallSkill.fxml"));
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
    private void buttonCochac2(ActionEvent event) {
        try {
            // Cargar la vista de LaLiga
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DRIBLLES/Coach2BallSkill.fxml"));
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
    private void buttonBack(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Forward.fxml"));
            Parent laLigaView = loader.load();

            Scene laLigaScene = new Scene(laLigaView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(laLigaScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
