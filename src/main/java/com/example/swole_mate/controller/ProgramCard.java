package com.example.swole_mate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ProgramCard {

    @FXML
    private Label difficultyLabel;

    @FXML
    private AnchorPane card;

    @FXML
    private Label duration;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label workoutNumbers;

    public void setDifficultyLabel(String difficulty) {
        difficultyLabel.setText(difficulty);
    }

    public void setDuration(String durationText) {
        duration.setText(durationText);
    }

    public void setPointsLabel(String points) {
        pointsLabel.setText(points);
    }

    public void setTypeLabel(String type) {
        typeLabel.setText(type);
    }

    public void setWorkoutNumbers(String numbers) {
        workoutNumbers.setText(numbers);
    }



}
