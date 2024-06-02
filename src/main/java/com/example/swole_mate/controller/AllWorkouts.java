package com.example.swole_mate.controller;

import com.example.swole_mate.Database.FoodDB;
import com.example.swole_mate.Database.ProgramDB;
import com.example.swole_mate.model.Food;
import com.example.swole_mate.model.Program;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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



    public void initialize() throws SQLException {
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
    }

}
