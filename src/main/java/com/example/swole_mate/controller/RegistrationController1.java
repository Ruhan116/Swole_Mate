package com.example.swole_mate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController1 {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void handleRegisterButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate the input fields

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            // Show an error message or prompt the user to fill all fields
            return;
        }

        if (!password.equals(confirmPassword)) {
            // Show an error message or prompt the user that passwords don't match
            return;
        }

        // Perform registration logic here, such as sending data to the server or saving to a database

        // Optionally, you can navigate to another page after successful registration
    }

    @FXML
    private void handleLoginLinkAction() {
        // Implement logic to navigate back to the login page
    }
}
