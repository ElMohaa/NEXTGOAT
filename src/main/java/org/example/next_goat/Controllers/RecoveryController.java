package org.example.next_goat.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.next_goat.Controllers.Physical.Force.ViewForce;
import org.example.next_goat.Controllers.Recovery.ViewKneeController;

import java.io.IOException;

public class RecoveryController {

    @FXML
    private void buttonForce1(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonForce2(ActionEvent event) {
        loadSkill(event, 2);
    }

    @FXML
    private void buttonForce3(ActionEvent event) {
        loadSkill(event, 3);
    }

    @FXML
    private void buttonForce4(ActionEvent event) {
        loadSkill(event, 4);
    }

    @FXML
    private void buttonForce5(ActionEvent event) {
        loadSkill(event, 5);
    }

    @FXML
    private void buttonForce6(ActionEvent event) {
        loadSkill(event, 6);
    }


    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Recovery/ViewRecovery.fxml"));
            Parent skillView = loader.load();

            ViewKneeController controller = loader.getController();
            controller.setRecoveNumber(skillNumber);

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/MainWindow.fxml"));
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
