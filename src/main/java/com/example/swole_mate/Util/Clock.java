package com.example.swole_mate.Util;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import static javafx.application.Application.launch;

public class Clock {
    private Label timeLabel;
    private Timeline timeline;
    private int minutes = 1; // Set the initial minutes
    private int seconds = 30; // Set the initial seconds

    private boolean finished;

    public static void main(String[] args) {
        launch(args);
    }

    public Clock(int minutes, int seconds, Label label)
    {
        this.minutes = minutes;
        this.seconds = seconds;
        this.timeLabel = label;
        this.finished = false;
    }

    public void startCountdown() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (seconds == 0) {
                if (minutes == 0) {
                    timeline.stop();
                    finished = true;
                } else {
                    minutes--;
                    seconds = 59;
                }
            } else {
                seconds--;
            }
            updateLabel();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void Pause() {
        timeline.pause();
    }

    public void Continue() {
        timeline.play();
    }



    public boolean getFinished()
    {
        return minutes == 0 && seconds == 0;
    }



    private void updateLabel() {
        String minuteString = String.format("%02d", minutes);
        String secondString = String.format("%02d", seconds);
        timeLabel.setText(minuteString + ":" + secondString);
    }
}
