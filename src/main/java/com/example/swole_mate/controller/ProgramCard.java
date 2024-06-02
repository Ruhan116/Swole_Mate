package com.example.swole_mate.controller;

import com.example.swole_mate.Main;
import com.example.swole_mate.Util.ProgramRunner;
import com.example.swole_mate.model.Program;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
    private Button startButton;

    @FXML
    private Label typeLabel;

    @FXML
    private Label workoutNumbers;

    private int programID;

    public void setDifficultyLabel(String difficulty) {
        difficultyLabel.setText(difficulty);
    }


    public void setID(int ID) {
        this.programID = ID;
    }

    public int getID() {
        return this.programID;
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

    @FXML
    void startExercise(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/swole_mate/view/Workouts/fullPage.fxml"));




            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1280, 720);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            WorkoutPageController workoutPageController = loader.getController();
            System.out.println(workoutPageController);

            ProgramRunner programRunner = new ProgramRunner(programID);
            programRunner.run(workoutPageController);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
