package org.example.next_goat.Controllers.ForAndMid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MidfielderController {

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
    private void controlball(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/DRIBLLES/BallControl.fxml"));
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
    private void topass(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/TOPASS/ToPass.fxml"));
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
    private void control(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/COTROLLBALL/ControllBall.fxml"));
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
    private void skill(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ForwardAndMid/SKILL/Habilidad.fxml"));
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


