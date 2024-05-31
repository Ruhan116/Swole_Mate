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
    void checkBMI(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/BMI.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1000, 617);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


}
