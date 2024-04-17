package com.example.swole_mate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class RegistrationController {
    @FXML
    private AnchorPane Main;

    @FXML
    private HBox hbox;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Rectangle r1;

    @FXML
    private ImageView i1;

    @FXML
    private AnchorPane child;

    @FXML
    private Label emailValidation;

    @FXML
    private Text text1;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField uName;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private PasswordField cPassword;

    @FXML
    private Label l1;

    @FXML
    private Label l5;

    @FXML
    private Label l6;

    @FXML
    private Button next;

    @FXML
    private Text text2;

    @FXML
    private Label fNameValidation;

    @FXML
    private Label lNameValidation;

    @FXML
    private Label userNameValidation;

    @FXML
    private Label passwordValidation;

    @FXML
    private ImageView loading;

    @FXML
    private Button exit;

    @FXML
    private Button back;

    @FXML
    private void initialize() {
        // Initialization code goes here
    }

    @FXML
    private void dragWindow(MouseEvent event) {
        // Dragging window code goes here
    }

    @FXML
    private void pressedWindow(MouseEvent event) {
        // Pressed window code goes here
    }

    @FXML
    private void clear(MouseEvent event) {
        // Clearing code goes here
    }

    @FXML
    private void nextForm(ActionEvent event) {
        // Code to handle moving to the next form goes here
    }

    @FXML
    private void close(ActionEvent event) {
        // Code to handle closing the window goes here
    }

    @FXML
    private void GoBackLogIn(ActionEvent event) {
        // Code to handle going back to the login page goes here
    }

}