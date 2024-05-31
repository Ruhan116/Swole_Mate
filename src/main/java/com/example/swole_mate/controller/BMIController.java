package com.example.swole_mate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BMIController {

    @FXML
    private TextField heightFeetTextField;

    @FXML
    private TextField heightInchesTextField;

    @FXML
    private TextField weightPoundsTextField;

    @FXML
    private TextField heightMetersTextField;

    @FXML
    private TextField weightKilogramsTextField;

    @FXML
    private Button standardButton;

    @FXML
    private Button metricButton;

    @FXML
    private Text bmiValueText;

    @FXML
    private Text bmiDescriptionText;

    @FXML
    void calculateBMI() {
        double heightInFeet = Double.parseDouble(heightFeetTextField.getText());
        double heightInInches = Double.parseDouble(heightInchesTextField.getText());
        double weightInPounds = Double.parseDouble(weightPoundsTextField.getText());


        double heightInMeters = ((heightInFeet * 12) + heightInInches) * 0.0254; // 1 inch = 0.0254 meters


        double weightInKilograms = weightInPounds * 0.453592; // 1 pound = 0.453592 kilograms


        double bmi = weightInKilograms / (heightInMeters * heightInMeters);


        bmiValueText.setText(String.format("%.2f", bmi));


        String bmiDescription;
        if (bmi < 18.5) {
            bmiDescription = "Underweight";
        }
        else if (bmi < 24.9) {
            bmiDescription = "Normal weight";
        }
        else if (bmi < 29.9) {
            bmiDescription = "Overweight";
        }
        else {
            bmiDescription = "Obese";
        }

        // Display BMI description
        bmiDescriptionText.setText(bmiDescription);
    }

    void switchToMetric() {
//        heightMetersTextField.setVisible(true);
//        weightKilogramsTextField.setVisible(true);
//
//        heightFeetTextField.setVisible(false);
//        heightInchesTextField.setVisible(false);
//        weightPoundsTextField.setVisible(false);
//
//        // Hide the standard button and show the metric button
//        standardButton.setVisible(true);
//        metricButton.setVisible(false);
    }

    @FXML
    void switchToStandard() {
//        heightFeetTextField.setVisible(true);
//        heightInchesTextField.setVisible(true);
//        weightPoundsTextField.setVisible(true);
//
//        heightMetersTextField.setVisible(false);
//        weightKilogramsTextField.setVisible(false);
//
//        // Hide the metric button and show the standard button
//        standardButton.setVisible(false);
//        metricButton.setVisible(true);
    }

    private void goToBMIView(ActionEvent event) {}
}

