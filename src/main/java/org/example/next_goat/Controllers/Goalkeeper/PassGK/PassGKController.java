package org.example.next_goat.Controllers.Goalkeeper.PassGK;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PassGKController {
    @FXML
    private void buttonSoot7(ActionEvent event) {
        loadSkill(event, 7);
    }

    @FXML
    private void buttonSoot8(ActionEvent event) {
        loadSkill(event, 8);
    }

    @FXML
    private void buttonSoot3(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonSoot4(ActionEvent event) {
        loadSkill(event, 4);
    }


    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Doorman/PassGK/ViewPassGK.fxml"));
            Parent skillView = loader.load();

            ViewPassGK controller = loader.getController();
            controller.setPassGkNumber(skillNumber);

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Doorman/Goalkeeper.fxml"));
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
