package com.example.swole_mate.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectGender {
    @FXML
    public void genderSelected(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(com.example.swole_mate.Main.class.getResource("view/BMI.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
