package org.example.next_goat.Controllers.BallControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SoleDragOutsideControll {
    @FXML
    private MediaView mediaView; // Cambiar a MediaView


    @FXML
    public void initialize() {
        // Cargar el video desde un archivo local
        String videoPath = getClass().getResource("/VIDEOS/SoleDragOutsidee.mp4").toExternalForm(); // Cambia esta ruta a la ubicación de tu video
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaView.setPreserveRatio(false);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        // Reproduce el video automáticamente
        mediaPlayer.play();

    }

    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/BALLCONTROL/Coach1BallControl.fxml"));
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
