package org.example.next_goat.Clases;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/WaitPage.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("/IMAGENS/Logo.jpeg"));

        primaryStage.getIcons().add(icon);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("NEXT-GOAT");
        primaryStage.setScene(new Scene(root, 315, 615));
        primaryStage.show();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));

        pause.setOnFinished(event -> {
            primaryStage.close();
        });

        pause.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
