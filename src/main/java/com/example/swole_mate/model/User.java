package com.example.swole_mate.model;

import com.example.swole_mate.Functions.functions;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    // Constructors
    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void mapResultSetToUser(ResultSet resultSet) throws SQLException {


        this.setId(Integer.parseInt(resultSet.getString("ID")));
        this.setUsername(resultSet.getString("USERNAME"));
        this.setPassword(resultSet.getString("PASSWORD"));
        this.setEmail(resultSet.getString("EMAIL"));


    }
}
