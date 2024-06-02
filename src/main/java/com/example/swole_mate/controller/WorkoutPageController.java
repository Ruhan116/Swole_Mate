package com.example.swole_mate.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.swole_mate.Util.Clock;

public class WorkoutPageController implements Initializable {

    @FXML
    private VBox Main;

    @FXML
    private MediaView mediaView;

    @FXML
    private Label clockLabel;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file = new File("src/main/resources/com/example/swole_mate/view/workoutVideos/antiantiHasib.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        mediaView.setPreserveRatio(true);

        double fixedWidth = 500; // You can set this to any width you desire
        mediaView.setFitWidth(fixedWidth);

        mediaPlayer.setOnReady(() -> {
            // Wait until the media is ready and its metadata is available
            double mediaWidth = media.getWidth();
            double mediaHeight = media.getHeight();
            if (mediaHeight > 0) { // Prevent division by zero
                mediaView.fitHeightProperty().bind(
                        mediaView.fitWidthProperty().multiply(mediaHeight / mediaWidth)
                );
            }
        });

        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));


        mediaPlayer.play();

        updateLabel();
    }


    public void updateLabel()
    {
        Clock clock = new Clock(0, 10, clockLabel);
        clock.startCountdown();
    }

}
