package com.example.swole_mate;

import com.example.swole_mate.Database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Workouts/AllWorkouts.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        // 658 417
        stage.setTitle("Swole-Mate");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        DatabaseManager databaseManager;
        DatabaseManager.createDatabase();

        launch();
    }
}