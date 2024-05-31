package com.example.swole_mate.Database;

import com.example.swole_mate.model.User;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private static final String DB_NAME = "SWOLEMATE";


    public static void main(String[] args) {
        try {
            createDatabase();  // Step 1: Ensure the database exists
            createUserTable();
            addUser();

            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection(DB_NAME);
            preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeConnection(connection);
        }
        return null;
    }

    public static void execute_update(String query, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection(DB_NAME);
            preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeConnection(connection);
        }
    }

    public static void createUserTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS USERS (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "USERNAME VARCHAR(20), " +
                "PASSWORD VARCHAR(20), " +
                "EMAIL VARCHAR(20))";

        execute_update(query);
    }

    public List<User> fetchAll(String tableName) throws SQLException {


        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try{
            ResultSet resultSet = execute_query(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                User pokemon = new User();
                pokemon.mapResultSetToUser(resultSet);
                userList.add(pokemon);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public User searchName(String tableName,String name) throws SQLException {

        User user = new User();
        String query = "SELECT * FROM " + tableName + " WHERE name = '" + name +"';";
//        System.out.println(query);
        try{
            ResultSet resultSet = execute_query(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                user.mapResultSetToUser(resultSet);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser() throws SQLException {
        User user = new User("Rifat", "Ruhan", "Wasif");

        String query = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES (?, ?, ?)";
        execute_update(query, user.getUsername(), user.getPassword(), user.getEmail());
        System.out.println("User successfully added");

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




    // Add methods to execute queries, updates, etc.
}
