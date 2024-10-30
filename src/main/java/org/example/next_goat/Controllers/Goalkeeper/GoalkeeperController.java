package org.example.next_goat.Controllers.Goalkeeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GoalkeeperController {
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Training.fxml"));
            Parent training = loader.load();

            Scene trainingScene = new Scene(training);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(trainingScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void bootonSoot(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Doorman/Soot/Soot.fxml"));
            Parent training = loader.load();

            Scene trainingScene = new Scene(training);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(trainingScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bootonPass(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Doorman/PassGK/PassGK.fxml"));
            Parent training = loader.load();

            Scene trainingScene = new Scene(training);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(trainingScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bootonControl(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Doorman/Control/ControlGK.fxml"));
            Parent training = loader.load();

            Scene trainingScene = new Scene(training);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(trainingScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
