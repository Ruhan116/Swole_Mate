package com.example.swole_mate.controller;

import com.example.swole_mate.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class InitialForm {

    @FXML
    private ToggleGroup Gender;

    @FXML
    private ToggleGroup GymPlace;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private RadioButton gymRadioButton;

    @FXML
    private RadioButton homeRadioButton;

    @FXML
    private Button submitButton;

    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public RadioButton getSelectedGender() {
        return (RadioButton) Gender.getSelectedToggle();
    }

    public RadioButton getSelectedGymPlace() {
        return (RadioButton) GymPlace.getSelectedToggle();
    }

    public void setSelectedGender(RadioButton gender) {
        this.Gender.selectToggle(gender);
    }

    public void setSelectedGymPlace(RadioButton gymPlace) {
        this.GymPlace.selectToggle(gymPlace);
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent e) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/data_calculator.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
