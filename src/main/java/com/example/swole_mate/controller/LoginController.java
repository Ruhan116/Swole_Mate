package com.example.swole_mate.controller;

import com.example.swole_mate.Database.UserDB;
import com.example.swole_mate.Functions.Hash;
import com.example.swole_mate.Main;
import com.example.swole_mate.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.xml.sax.helpers.ParserAdapter;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    @FXML
    private AnchorPane anchorpane_login;

    @FXML
    private TextField EmailField;

    @FXML
    private PasswordField PassField;

    @FXML
    private CheckBox response;

    @FXML
    private Text errMsg;

    @FXML
    private Button LogInButton;

    @FXML
    private Button exit;

    @FXML
    private Label epValidation;

    @FXML
    private Label passwordValidation;

    @FXML
    private Button back;

    @FXML
    private Button register;

    @FXML
    void clear(MouseEvent event) {
        // Add logic to clear validation labels or fields if needed
    }

    @FXML
    void close() {
        // Add logic to close the application window
    }

    @FXML
    void dragged(MouseEvent event) {
        // Add logic to handle window dragging
    }

    @FXML
    void loginBtn(MouseEvent e) throws SQLException {
        String username = EmailField.getText();
        String password = PassField.getText();

        UserDB userdb = new UserDB();
        User user = userdb.searchName(username);
        System.out.println(user);

        if(user.getUsername() != null)
        {
            Hash hash = new Hash();
            if(Hash.verifyPassword(password, user.getPassword()))
            {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Main_Dashboard.fxml"));
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
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
            else {
                errMsg.setText("Incorrect Password. Please try again.");
            }
        }
        else{
            errMsg.setText("This account does not exist. Please try again.");
        }


    }

    @FXML
    void GoBackLogIn() {
        // Add logic to navigate back to previous screen
    }

    @FXML
    void pressed(MouseEvent event) {
        // Add logic to handle window dragging

    }

    @FXML
    void acc_register(ActionEvent e) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/initial_form.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
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
