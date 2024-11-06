package org.example.next_goat.Controllers.Defen.ControllDefense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.next_goat.Controllers.Defen.*;

import java.io.IOException;

public class ControllDefense {
    @FXML
    private void buttonControlll1(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonControlll2(ActionEvent event) {
        loadSkill(event, 2);
    }

    @FXML
    private void buttonControlll3(ActionEvent event) {
        loadSkill(event, 3);
    }

    @FXML
    private void buttonControlll4(ActionEvent event) {
        loadSkill(event, 4);
    }



    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Defense/ControllBallDefense/ViewDefenseControll.fxml"));
            Parent skillView = loader.load();

            ViewDefenseControll controller = loader.getController();
            controller.setControllNumber(skillNumber); // Establece el número de habilidad

            Scene skillScene = new Scene(skillView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(skillScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void buttonBack(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Defense/Defense.fxml"));
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