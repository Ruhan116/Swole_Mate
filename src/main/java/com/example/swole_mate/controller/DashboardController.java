package com.example.swole_mate.controller;

import com.example.swole_mate.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    // Your existing controller code

    @FXML
    private void minimizeBtn(ActionEvent event) {
        // Define the behavior for minimizing the window here
    }

    @FXML
    private void maximizeBtn(ActionEvent event) {
        // Define the behavior for maximizing the window here
    }

    @FXML
    private void close(ActionEvent event) {
        // Define the behavior for closing the window here
    }

    @FXML
    void goToExercise(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Workouts/AllWorkouts.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    @FXML
    void goToFood(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/FoodTracker/DietTracker.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
