package org.example.next_goat.Controllers.Defen.Seated;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.next_goat.Controllers.ForAndMid.ControllBall.ControllBallView;

import java.io.IOException;

public class SeatedController {
    @FXML
    private void buttonSeatedlll1(ActionEvent event) {
        loadSkill(event, 1);
    }

    @FXML
    private void buttonSeatedlll2(ActionEvent event) {
        loadSkill(event, 2);
    }

    @FXML
    private void buttonSeatedlll3(ActionEvent event) {
        loadSkill(event, 3);
    }

    @FXML
    private void buttonSeatedlll4(ActionEvent event) {
        loadSkill(event, 4);
    }



    private void loadSkill(ActionEvent event, int skillNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Defense/SEATED/ViewSeated.fxml"));
            Parent skillView = loader.load();

            ViewSeatedController controller = loader.getController();
            controller.setSeatedlNumber(skillNumber);

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
