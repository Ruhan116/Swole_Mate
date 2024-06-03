package com.example.swole_mate.controller;

import com.example.swole_mate.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.swole_mate.Database.FoodDB;
import com.example.swole_mate.model.Food;

public class DietTracker {
    @FXML
    private TextField searchBox;

    @FXML
    private Button backButton;
    @FXML
    private AnchorPane loadFood;

    private AutoCompletionBinding<String> autoCompletionBinding;
    private Set<String> possibleSuggestions = new HashSet<>();

    public double total_protein;
    public double total_fats;
    public double total_carbs;
    public double total_cals;

    @FXML
    private Label protLabel;
    @FXML
    private Label fatLabel;
    @FXML
    private Label carbsLabel;
    @FXML
    private Label calsLabel;

    @FXML
    private PieChart nutrientPieChart;
    private ObservableList<PieChart.Data> pieChartData;

    @FXML
    public void initialize() {
        try {
            total_fats = 0;
            total_carbs = 0;
            total_protein = 0;
            total_cals = 0;

            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Protein", total_protein),
                    new PieChart.Data("Fats", total_fats),
                    new PieChart.Data("Carbs", total_carbs),
                    new PieChart.Data("Calories", total_cals)
            );
            nutrientPieChart.setData(pieChartData);

            List<Food> foodList = FoodDB.fetchAll();

            for (Food food : foodList) {
                possibleSuggestions.add(food.getName());
            }

            autoCompletionBinding = TextFields.bindAutoCompletion(searchBox, possibleSuggestions);

            searchBox.setOnKeyPressed(this::handleEnterKey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Event Handling
    private void handleEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/swole_mate/view/FoodTracker/FoodInfo.fxml"));
                AnchorPane foodInfoPane = fxmlLoader.load();

                FoodInfo foodInfoController = fxmlLoader.getController();
                foodInfoController.setDietTrackerController(this);

                String selectedFoodName = searchBox.getText();
                Food selectedFood = FoodDB.searchName(selectedFoodName);
                foodInfoController.setFood(selectedFood);

                loadFood.getChildren().setAll(foodInfoPane);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) {
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

    public void clearLoadFoodPane() {
        loadFood.getChildren().clear();
    }

    public void updateTotals(Food food, int servingSize) {
        total_protein += food.getProtein() * servingSize;
        total_fats += food.getFat() * servingSize;
        total_carbs += food.getCarbs() * servingSize;
        total_cals += food.getCalorie() * servingSize;

        System.out.println(total_fats);

        protLabel.setText(Double.toString(total_protein) + " g");
        fatLabel.setText(Double.toString(total_fats) + " g");
        calsLabel.setText(Double.toString(total_cals) + " cals");
        carbsLabel.setText(Double.toString(total_carbs) + " g");

        // Update pie chart data
        pieChartData.get(0).setPieValue(total_protein);
        pieChartData.get(1).setPieValue(total_fats);
        pieChartData.get(2).setPieValue(total_carbs);
        pieChartData.get(3).setPieValue(total_cals);
    }
}
