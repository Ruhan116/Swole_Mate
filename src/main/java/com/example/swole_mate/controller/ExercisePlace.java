package com.example.swole_mate.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ExercisePlace {

    @FXML
    public void gymPlaceDone(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(com.example.swole_mate.Main.class.getResource("view/gender_selection.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1000, 617);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
