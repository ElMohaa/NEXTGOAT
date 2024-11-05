package org.example.next_goat.Controllers.ForAndMid.Skill;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HabilidadControll {

    @FXML
    private void buttonSkill1(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonSkill2(ActionEvent event) {
        loadSkill(event, 2);
    }

    @FXML
    private void buttonSkill3(ActionEvent event) {
        loadSkill(event, 3);
    }

    @FXML
    private void buttonSkill4(ActionEvent event) {
        loadSkill(event, 4);
    }

    @FXML
    private void buttonSkill5(ActionEvent event) {
        loadSkill(event, 5);
    }

    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/SKILL/SkillView.fxml"));
            Parent skillView = loader.load();

            SkillController controller = loader.getController();
            controller.setSkillNumber(skillNumber); // Establece el número de habilidad

            Scene skillScene = new Scene(skillView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(skillScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/Forward.fxml"));
            Parent habilidadView = loader.load();

            Scene habilidadScene = new Scene(habilidadView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(habilidadScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
