package com.example.swole_mate.controller;

import com.example.swole_mate.Database.FoodDB;
import com.example.swole_mate.Database.ProgramDB;
import com.example.swole_mate.model.Food;
import com.example.swole_mate.model.Program;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AllWorkouts {

    @FXML
    private GridPane dailyGrid;

    @FXML
    private GridPane programGrid;

    @FXML
    private GridPane recordGrid;

    @FXML
    private GridPane weeklyGrid;

    @FXML
    private AnchorPane dailyTrack;


    @FXML
    private AnchorPane recordTrack;

    @FXML
    private AnchorPane weeklyTrack;



    public void initialize() throws SQLException, IOException {
        // Example of populating the grid with ProgramCards

        ProgramDB programDB = new ProgramDB();
        List<Program> programs =  ProgramDB.fetchAll();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                try {
                    // Load ProgramCard
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/swole_mate/view/Workouts/programCard.fxml"));
                    AnchorPane programCard = loader.load();

                    Program program = programs.get(row * 3 + col);

                    // Get controller and set data
                    ProgramCard programCardController = loader.getController();
                    programCardController.setDifficultyLabel(program.getDifficulty());
                    programCardController.setDuration(program.getDuration());
                    programCardController.setPointsLabel(Integer.toString(program.getPoints()));
                    programCardController.setTypeLabel(program.getType());
                    programCardController.setWorkoutNumbers(Integer.toString(program.getWorkoutNumbers()));
                    programCardController.setID(program.getID());

                    // Add ProgramCard to GridPane
                    programGrid.add(programCard, col, row);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Load daily values
        // Load daily values
        FXMLLoader dailyvaluesLoader = new FXMLLoader(getClass().getResource("/com/example/swole_mate/view/Workouts/values.fxml"));
        AnchorPane dailyTrackValue = dailyvaluesLoader.load();
        Values dailyValue = dailyvaluesLoader.getController();
        dailyValue.setDistanceLabel(5);
        dailyValue.setCaloriesLabel(6);
        dailyValue.setDurationLabel(7);
        dailyValue.setPointsLabel(8);

// Set the loaded content into the AnchorPane
        dailyTrack.getChildren().setAll(dailyTrackValue);

// Adjust the size of the loaded content to match the size of the AnchorPane
        dailyTrackValue.prefWidthProperty().bind(dailyTrack.widthProperty());
        dailyTrackValue.prefHeightProperty().bind(dailyTrack.heightProperty());

// Load weekly values
        FXMLLoader weeklyvaluesLoader = new FXMLLoader(getClass().getResource("/com/example/swole_mate/view/Workouts/values.fxml"));
        AnchorPane weeklyTrackValue = weeklyvaluesLoader.load();
        Values weeklyValue = weeklyvaluesLoader.getController();
        weeklyValue.setDistanceLabel(5);
        weeklyValue.setCaloriesLabel(6);
        weeklyValue.setDurationLabel(7);
        weeklyValue.setPointsLabel(8);

// Set the loaded content into the AnchorPane
        weeklyTrack.getChildren().setAll(weeklyTrackValue);

// Adjust the size of the loaded content to match the size of the AnchorPane
        weeklyTrackValue.prefWidthProperty().bind(weeklyTrack.widthProperty());
        weeklyTrackValue.prefHeightProperty().bind(weeklyTrack.heightProperty());

// Load record values
        FXMLLoader recordvaluesLoader = new FXMLLoader(getClass().getResource("/com/example/swole_mate/view/Workouts/values.fxml"));
        AnchorPane recordTrackValue = recordvaluesLoader.load();
        Values recordValue = recordvaluesLoader.getController();
        recordValue.setDistanceLabel(5);
        recordValue.setCaloriesLabel(6);
        recordValue.setDurationLabel(7);
        recordValue.setPointsLabel(8);

// Set the loaded content into the AnchorPane
        recordTrack.getChildren().setAll(recordTrackValue);

// Adjust the size of the loaded content to match the size of the AnchorPane
        recordTrackValue.prefWidthProperty().bind(recordTrack.widthProperty());
        recordTrackValue.prefHeightProperty().bind(recordTrack.heightProperty());

    }

}
