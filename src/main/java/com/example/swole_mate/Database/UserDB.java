package com.example.swole_mate.Database;


import com.example.swole_mate.model.User;
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

public class UserDB {


    public static void main(String[] args) throws SQLException {
        createUserTable();
    }

    public static void createUserTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS USERS (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "USERNAME VARCHAR(50) NOT NULL, " +
                "PASSWORD VARCHAR(255) NOT NULL, " +
                "EMAIL VARCHAR(100) NOT NULL UNIQUE, " +
                "FIRST_NAME VARCHAR(50), " +
                "LAST_NAME VARCHAR(50), " +
                "PHONE_NUMBER VARCHAR(20), " +
                "JOIN_DATE DATE NOT NULL " +
                ")";

        execute_update(query);
    }

    public static List<User> fetchAll() throws SQLException {

        String tableName = "USERS";
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try{
            ResultSet resultSet = execute_query(query);

            while(resultSet.next())
            {
                User user = new User();
                user.mapResultSetToUser(resultSet);
                userList.add(user);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public static void displayAll() throws SQLException {
        List<User> userList = fetchAll();
        for(User user: userList)
        {
            user.Display();
        }
    }
    public static User searchName(String username) throws SQLException {
        String tableName = "USERS";
        User user = new User();
        String query = "SELECT * FROM " + tableName + " WHERE username = '" + username +"';";

        try{
            ResultSet resultSet = execute_query(query);
            while (resultSet.next()) {
                user.mapResultSetToUser(resultSet);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser(String username, String password, String email, String firstName, String lastName, String phoneNumber) throws SQLException {
        LocalDate joinDate = LocalDate.now();

        String query = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER, JOIN_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
        execute_update(query, username, password, email, firstName, lastName, phoneNumber, Date.valueOf(joinDate));
        System.out.println("User successfully added");
    }


    public static void addUser(String username, String password, String email) throws SQLException {
        LocalDate joinDate = LocalDate.now();

        String query = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, JOIN_DATE) VALUES (?, ?, ?, ?)";
        execute_update(query, username, password, email,Date.valueOf(joinDate));
        System.out.println("User successfully added");
    }


}
