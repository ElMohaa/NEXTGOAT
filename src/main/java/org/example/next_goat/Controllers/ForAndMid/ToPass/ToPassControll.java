package org.example.next_goat.Controllers.ForAndMid.ToPass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.next_goat.Controllers.ForAndMid.Skill.SkillController;

import java.io.IOException;

public class ToPassControll {

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/TOPASS/PassView.fxml"));
            Parent skillView = loader.load();

            PassViewController controller = loader.getController();
            controller.setPassNumber(skillNumber); // Establece el número de habilidad

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/Forward.fxml"));
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
