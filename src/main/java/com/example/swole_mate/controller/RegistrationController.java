package com.example.swole_mate.controller;

import com.example.swole_mate.Main;
import com.example.swole_mate.util.DatabaseManager;
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
    private PasswordField password;

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

    public void registerUser(ActionEvent event) {
        // 1. Get user input
        String firstName = fName.getText();
        String lastName = lName.getText();
        String username = uName.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();
        String userPasswordCopy = cPassword.getText();

        String checkUsernameQuery = "SELECT COUNT(*) FROM mydatabase.users WHERE username = ?";
        try (Connection connectDB = DatabaseManager.getConnection();
             PreparedStatement statement = connectDB.prepareStatement(checkUsernameQuery)) {
            statement.setString(1, username);

            ResultSet queryOutput = statement.executeQuery();
            queryOutput.next();

            int existingUsers = queryOutput.getInt(1);
            if (existingUsers > 0) {
                userNameValidation.setText("Username already exists!");
                return;
            }
            else if(!userPassword.equals(userPasswordCopy)){
                userNameValidation.setText("Password does not match.");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String insertUserQuery = "INSERT INTO mydatabase.users (first_name, last_name, username, password, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection connectDB = DatabaseManager.getConnection();
             PreparedStatement statement = connectDB.prepareStatement(insertUserQuery)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, userPassword);
            statement.setString(5, userEmail);

            statement.executeUpdate();

            System.out.println("User registered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(com.example.swole_mate.Main.class.getResource("view/exercise_place.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Swole-Mate");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

    }


}
