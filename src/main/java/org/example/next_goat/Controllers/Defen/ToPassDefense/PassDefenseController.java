package org.example.next_goat.Controllers.Defen.ToPassDefense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.next_goat.Controllers.ForAndMid.ToPass.PassViewController;

import java.io.IOException;

public class PassDefenseController {
    @FXML
    private void buttonPass1(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonPass2(ActionEvent event) {
        loadSkill(event, 2);
    }

    @FXML
    private void buttonPass3(ActionEvent event) {
        loadSkill(event, 3);
    }

    @FXML
    private void buttonPass4(ActionEvent event) {
        loadSkill(event, 4);
    }

    @FXML
    private void buttonPass5(ActionEvent event) {
        loadSkill(event, 5);
    }
    @FXML
    private void buttonPass6(ActionEvent event) {
        loadSkill(event, 6);
    }

    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Defense/PassDefense/ViewPassDefense.fxml"));
            Parent skillView = loader.load();

            ViewPassControll controller = loader.getController();
            controller.setPassNumber(skillNumber);

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
