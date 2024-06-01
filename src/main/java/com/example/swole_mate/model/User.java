package com.example.swole_mate.model;

import com.example.swole_mate.Functions.functions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate joinDate;

    // Default constructor
    public User() {}

    // Constructor for creating a new user
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.joinDate = LocalDate.now();
    }

    // Full constructor
    public User(int id, String username, String password, String email, String firstName, String lastName, String phoneNumber, LocalDate joinDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.joinDate = joinDate;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
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

    public void Display() {
        System.out.println("ID: " + id);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Phone Number: " + phoneNumber);
        if(joinDate != null) System.out.println("Joining Date: " + joinDate);

    }

    public void mapResultSetToUser(ResultSet resultSet) throws SQLException {
        this.setId(resultSet.getInt("ID"));
        this.setUsername(resultSet.getString("USERNAME"));
        this.setPassword(resultSet.getString("PASSWORD"));
        this.setEmail(resultSet.getString("EMAIL"));
        this.setFirstName(resultSet.getString("FIRST_NAME"));
        this.setLastName(resultSet.getString("LAST_NAME"));
        this.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
        this.setJoinDate(resultSet.getDate("JOIN_DATE").toLocalDate());
    }
}
