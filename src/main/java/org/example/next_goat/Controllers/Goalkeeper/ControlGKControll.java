package org.example.next_goat.Controllers.Goalkeeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.next_goat.Controllers.Goalkeeper.Soot.ViewSoot;

import java.io.IOException;

public class ControlGKControll {
    @FXML
    private void buttonSoot1(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonSoot2(ActionEvent event) {
        loadSkill(event, 2);
    }

    @FXML
    private void buttonSoot3(ActionEvent event) {
        loadSkill(event, 3);
    }

    @FXML
    private void buttonSoot4(ActionEvent event) {
        loadSkill(event, 4);
    }

    @FXML
    private void buttonSoot5(ActionEvent event) {
        loadSkill(event, 5);
    }
    @FXML
    private void buttonSoot6(ActionEvent event) {
        loadSkill(event, 6);
    }

    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Doorman/Control/ViewControlGK.fxml"));
            Parent skillView = loader.load();

            ViewControlGK controller = loader.getController();
            controller.setControltNumber(skillNumber);

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
