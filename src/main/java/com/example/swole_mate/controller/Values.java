package com.example.swole_mate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Values {

    @FXML
    private Label caloriesLabel;

    @FXML
    private Label distanceLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private Label pointsLabel;


    public Label getCaloriesLabel() {
        return caloriesLabel;
    }


    public void setCaloriesLabel(int x) {
        caloriesLabel.setText(String.valueOf(x));
    }

    public Label getDistanceLabel() {
        return distanceLabel;
    }

    public void setDistanceLabel(int x) {
        this.distanceLabel.setText(String.valueOf(x));
    }

    public Label getDurationLabel() {
        return durationLabel;
    }

    public void setDurationLabel(int x) {
        this.durationLabel.setText(String.valueOf(x));
    }

    public Label getPointsLabel() {
        return pointsLabel;
    }

    public void setPointsLabel(int x) {
        this.pointsLabel.setText(String.valueOf(x));
    }
}
