package com.example.swole_mate.controller;

import com.example.swole_mate.model.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FoodInfo {

    @FXML
    private Label foodNameLabel;

    @FXML
    private Label protein;

    @FXML
    private Label fat;

    @FXML
    private Label carbs;

    @FXML
    private Label cals;

    @FXML
    private Label serve;

    @FXML
    private TextField serving_size;

    @FXML
    ImageView img;

    private DietTracker dietTrackerController;

    public void setFood(Food food) {
        foodNameLabel.setText(food.getName());
        serve.setText(String.valueOf(food.getQuantity()));
        protein.setText(String.valueOf(food.getProtein()));
        fat.setText(String.valueOf(food.getFat()));
        carbs.setText(String.valueOf(food.getCarbs()));
        cals.setText(String.valueOf(food.getCalorie()));

        Image image = new Image("/com/example/swole_mate/view/images/" +food.getName() + ".png");
        img.setImage(image);
        serving_size.setOnAction(event -> {
            try {
                int servingSize = Integer.parseInt(serving_size.getText());
                dietTrackerController.updateTotals(food, servingSize);
                dietTrackerController.clearLoadFoodPane();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Optionally handle invalid input
            }
        });
    }

    public void setDietTrackerController(DietTracker dietTrackerController) {
        this.dietTrackerController = dietTrackerController;
    }
}
