package com.example.swole_mate.Database;


import com.example.swole_mate.model.Food;
import com.example.swole_mate.Functions.Hash;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.swole_mate.Database.DatabaseManager.execute_query;
import static com.example.swole_mate.Database.DatabaseManager.execute_update;

public class FoodDB {


    public static void main(String[] args) throws SQLException {
        createFoodTable();

        for(Food food: prefixSearch("Paa"))
        {
            System.out.println(food.getName());
        }
    }

//    Food,Quantity,Calories,Protein,Fat,Carbohydrates
    public static void createFoodTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS FOODS (" +
                "NAME VARCHAR(50) PRIMARY KEY, " +
                "QUANTITY VARCHAR(50) NOT NULL, " +
                "CALORIES DECIMAL(10,2) NOT NULL, " +
                "PROTEIN DECIMAL(10,2) NOT NULL, " +
                "FAT DECIMAL(10,2), " +
                "CARBS DECIMAL(10,2)" +
                ")";

        execute_update(query);
    }

    public static List<Food> fetchAll() throws SQLException {

        String tableName = "FOODS";
        List<Food> foodList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try{
            ResultSet resultSet = execute_query(query);

            while(resultSet.next())
            {
                Food food = new Food();
                food.mapResultSetToFood(resultSet);
                foodList.add(food);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return foodList;
    }

//    public static void displayAll() throws SQLException {
//        List<Food> foodList = fetchAll();
//        for(Food food: foodList)
//        {
//            food.Display();
//        }
//    }
    public static Food searchName(String name) throws SQLException {
        String tableName = "USERS";
        Food food = new Food();
        String query = "SELECT * FROM " + tableName + " WHERE NAME = '" + name +"';";

        try{
            ResultSet resultSet = execute_query(query);
            while (resultSet.next()) {
                food.mapResultSetToFood(resultSet);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return food;
    }


    public static List<Food> prefixSearch(String name) throws SQLException {

        String tableName = "FOODS";
        List<Food> foodList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE NAME like '" + name +"%';";

        try{
            ResultSet resultSet = execute_query(query);

            while(resultSet.next())
            {
                Food food = new Food();
                food.mapResultSetToFood(resultSet);
                foodList.add(food);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return foodList;
    }


    public static void addFood(String name, String quantity, Double calorie, Double protein, Double fat, Double carbs) throws SQLException {
        String query = "INSERT INTO foods (NAME, QUANTITY, CALORIES, PROTEIN, FAT, CARBS) VALUES (?, ?, ?, ?, ?, ?)";
        execute_update(query, name, quantity, calorie, protein, fat, carbs);
        System.out.println("Food successfully added");
    }



}
