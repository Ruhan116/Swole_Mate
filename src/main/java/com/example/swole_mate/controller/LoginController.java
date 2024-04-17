package com.example.swole_mate.controller;

import com.example.swole_mate.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();


        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        System.out.println("Login successful!");
    }

    @FXML
    private void handleRegisterLinkAction(MouseEvent e) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/registration_screen_1.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
