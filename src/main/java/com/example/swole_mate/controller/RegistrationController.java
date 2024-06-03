package com.example.swole_mate.controller;

import com.example.swole_mate.Database.DatabaseManager;
import com.example.swole_mate.Database.UserDB;
import com.example.swole_mate.Functions.Hash;
import com.example.swole_mate.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private Text errorMessage;

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

    public void registerUser(ActionEvent event) throws SQLException {
        // 1. Get user input
        UserDB userdb;
        UserDB.createUserTable();

        String username = uName.getText();
        String email = this.email.getText();
        String password = this.password.getText(); // Assuming password is hashed before storing
        String first_name = this.fName.getText();
        String last_name = this.lName.getText();


        System.out.println("Reg Button Clicked");



        if(UserDB.searchName(username).getUsername() != null)
        {
            errorMessage.setText("Account already exists.");
        }
        else
        {
            Hash hash;
            password = Hash.hashPassword(password);

            UserDB.addUser(username, password, email, first_name, last_name, "017827");

            try{
                FXMLLoader fxmlLoader = new FXMLLoader(com.example.swole_mate.Main.class.getResource("view/initial_form.fxml"));

                InitialForm controller = fxmlLoader.getController();

                // Set the data in the controller
                controller.setName(username);

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


}
