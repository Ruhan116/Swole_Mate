package com.example.swole_mate.controller;

import com.example.swole_mate.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PointsAdded {

    @FXML
    private Button continueButton;

    @FXML
    void cont(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Main_Dashboard.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
