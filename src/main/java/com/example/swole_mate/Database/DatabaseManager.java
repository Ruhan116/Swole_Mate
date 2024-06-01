package com.example.swole_mate.Database;

import com.example.swole_mate.model.User;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import static com.example.swole_mate.Database.UserDB.createUserTable;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private static final String DB_NAME = "SWOLEMATE";


    public static void main(String[] args) {

    }
    public static Connection getConnection(String dbNameString) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL + "/" + dbNameString, USERNAME, PASSWORD);
            System.out.println("Connection established!!");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to database.");
        }
    }


    public static void createDatabase() throws SQLException {
        String query = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection("");
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeConnection(connection);
        }

        System.out.println("Database Created!");
    }

    public static ResultSet execute_query(String query, Object... params) throws SQLException {
        Connection connection = getConnection(DB_NAME);
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        return preparedStatement.executeQuery();
    }

    public static void execute_update(String query, Object... params) throws SQLException {
        Connection connection = getConnection(DB_NAME);
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        preparedStatement.executeUpdate();
    }


    // Close the connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
