package com.example.swole_mate.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Food {

    private String name;
    private String quantity;
    private Double calorie;
    private Double protein;
    private Double fat;
    private Double carbs;

    // Default constructor
    public Food() {}

    // Parameterized constructor
    public Food(String name, String quantity, Double calorie, Double protein, Double fat, Double carbs) {
        this.name = name;
        this.quantity = quantity;
        this.calorie = calorie;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public void mapResultSetToFood(ResultSet resultSet) throws SQLException {
        this.setName(resultSet.getString("NAME"));
        this.setQuantity(resultSet.getString("QUANTITY"));
        this.setCalorie(resultSet.getDouble("CALORIES"));
        this.setProtein(resultSet.getDouble("PROTEIN"));
        this.setFat(resultSet.getDouble("FAT"));
        this.setCarbs(resultSet.getDouble("CARBS"));
    }

}

