package org.example.next_goat.Controllers.Physical.Speed;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.next_goat.Controllers.Physical.Force.ViewForce;

import java.io.IOException;

public class SpeedController {

    @FXML
    private void buttonSpeed1(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonSpeed2(ActionEvent event) {
        loadSkill(event, 2);
    }

    @FXML
    private void buttonSpeed3(ActionEvent event) {
        loadSkill(event, 3);
    }




    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Physical/ViewSpeed.fxml"));
            Parent skillView = loader.load();

            ViewSpeed controller = loader.getController();
            controller.setSpeedNumber(skillNumber);

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Physical.fxml"));
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
