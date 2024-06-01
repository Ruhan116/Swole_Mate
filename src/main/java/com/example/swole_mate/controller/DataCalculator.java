package com.example.swole_mate.controller;

import com.example.swole_mate.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class DataCalculator {
    @FXML
    TextField heightFeet;
    @FXML
    TextField heightInches;
    @FXML
    TextField weight;
    @FXML
    TextField neckSize;
    @FXML
    TextField waistSize;


    @FXML
    private void calculateData() {
        try {
            int feet = Integer.parseInt(heightFeet.getText());
            int inches = Integer.parseInt(heightInches.getText());
            double weightKg = Double.parseDouble(weight.getText());
            double neckCm = Double.parseDouble(neckSize.getText());
            double waistCm = Double.parseDouble(waistSize.getText());


            double heightCm = feet * 30.48 + inches * 2.54;


            double bmi = calculateBMI(weightKg, heightCm);


            double bmr = calculateBMR(weightKg, heightCm);


            double bodyFatPercentage = calculateBodyFatPercentage(heightCm, neckCm, waistCm);


            showAlert("Results", "BMI: " + String.format("%.2f", bmi) + "\nBMR: " + String.format("%.2f", bmr) + "\nBody Fat Percentage: " + String.format("%.2f", bodyFatPercentage) + "%");
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numerical values.");
        }
    }

    @FXML
    void submitForm(ActionEvent e){

        //
        //Database Work
        //

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/login.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private double calculateBMI(double weightKg, double heightCm) {
        double heightM = heightCm / 100;
        return weightKg / (heightM * heightM);
    }

    private double calculateBMR(double weightKg, double heightCm) {
        double heightM = heightCm / 100;
        int age = 25;
        return 10 * weightKg + 6.25 * heightCm - 5 * age + 5;
    }

    private double calculateBodyFatPercentage(double heightCm, double neckCm, double waistCm) {
        return 86.010 * Math.log10(waistCm - neckCm) - 70.041 * Math.log10(heightCm) + 36.76;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}