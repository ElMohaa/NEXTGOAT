package org.example.next_goat.Controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class WaitController {
    @FXML
    private void initialize() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        pause.setOnFinished(event -> {
            cargarSiguienteFXML();
        });

        pause.play();
    }

    public void cargarSiguienteFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/UserLogin.fxml"));
            Parent root = loader.load();

            UserLoginController controller = loader.getController();

            Image icon = new Image(getClass().getResourceAsStream("/IMAGENS/Logo.jpeg"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(icon);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("NEXT-GOAT");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
