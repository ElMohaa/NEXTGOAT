package org.example.next_goat.Controllers.ForAndMid.Dribble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.next_goat.Controllers.ForAndMid.Skill.SkillController;

import java.io.IOException;

public class BallControll {

    @FXML
    private void buttonDriblle1(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonDriblle2(ActionEvent event) {
        loadSkill(event, 2);
    }

    @FXML
    private void buttonDriblle3(ActionEvent event) {
        loadSkill(event, 3);
    }

    @FXML
    private void buttonDriblle4(ActionEvent event) {
        loadSkill(event, 4);
    }

    @FXML
    private void buttonDriblle5(ActionEvent event) {
        loadSkill(event, 5);
    }

    @FXML
    private void buttonDriblle6(ActionEvent event) {
        loadSkill(event, 6);
    }
    @FXML
    private void buttonDriblle7(ActionEvent event) {
        loadSkill(event, 7);
    }

    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/DRIBLLES/Driblle.fxml"));
            Parent skillView = loader.load();

            DriblleController controller = loader.getController();
            controller.setDriblleNumber(skillNumber); // Establece el número de habilidad

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
