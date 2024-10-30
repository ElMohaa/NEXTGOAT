package org.example.next_goat.Controllers.Defen.ToPassDefense;

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

public class ViewPassControll {
    @FXML
    private MediaView mediaView;

    private int skillNumber; // Almacena el número de habilidad

    public void setPassNumber(int skillNumber) {
        this.skillNumber = skillNumber;
        loadVideo();
    }

    private void loadVideo() {
        String videoPath = getClass().getResource("/VIDEOS/pass" + skillNumber + ".mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaView.setPreserveRatio(false);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }
    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Defense/PassDefense/PassDefense.fxml"));
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
