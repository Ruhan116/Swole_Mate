package com.example.swole_mate.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Program {

    private String type;
    private String difficulty;
    private String duration;
    private int workoutNumbers;
    private int points;
    private int exerciseId1;
    private int exerciseId1_duration;
    private int exerciseId2;
    private int exerciseId2_duration;
    private int exerciseId3;
    private int exerciseId3_duration;

    // Default constructor
    public Program() {}

    // Parameterized constructor
    public Program(String type, String difficulty, String duration, int workoutNumbers, int points,
                   int exerciseId1, int exerciseId1_duration, int exerciseId2, int exerciseId2_duration,
                   int exerciseId3, int exerciseId3_duration) {
        this.type = type;
        this.difficulty = difficulty;
        this.duration = duration;
        this.workoutNumbers = workoutNumbers;
        this.points = points;
        this.exerciseId1 = exerciseId1;
        this.exerciseId1_duration = exerciseId1_duration;
        this.exerciseId2 = exerciseId2;
        this.exerciseId2_duration = exerciseId2_duration;
        this.exerciseId3 = exerciseId3;
        this.exerciseId3_duration = exerciseId3_duration;
    }


    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getWorkoutNumbers() {
        return workoutNumbers;
    }

    public void setWorkoutNumbers(int workoutNumbers) {
        this.workoutNumbers = workoutNumbers;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getExerciseId1() {
        return exerciseId1;
    }

    public void setExerciseId1(int exerciseId1) {
        this.exerciseId1 = exerciseId1;
    }

    public int getExerciseId1_duration() {
        return exerciseId1_duration;
    }

    public void setExerciseId1_duration(int exerciseId1_duration) {
        this.exerciseId1_duration = exerciseId1_duration;
    }

    public int getExerciseId2() {
        return exerciseId2;
    }

    public void setExerciseId2(int exerciseId2) {
        this.exerciseId2 = exerciseId2;
    }

    public int getExerciseId2_duration() {
        return exerciseId2_duration;
    }

    public void setExerciseId2_duration(int exerciseId2_duration) {
        this.exerciseId2_duration = exerciseId2_duration;
    }

    public int getExerciseId3() {
        return exerciseId3;
    }

    public void setExerciseId3(int exerciseId3) {
        this.exerciseId3 = exerciseId3;
    }

    public int getExerciseId3_duration() {
        return exerciseId3_duration;
    }

    public void setExerciseId3_duration(int exerciseId3_duration) {
        this.exerciseId3_duration = exerciseId3_duration;
    }

    public void mapResultSetToProgram(ResultSet resultSet) throws SQLException {
        this.setType(resultSet.getString("TYPE"));
        this.setDifficulty(resultSet.getString("DIFFICULTY"));
        this.setDuration(resultSet.getString("DURATION"));
        this.setWorkoutNumbers(resultSet.getInt("WORKOUT_NUMBERS"));
        this.setPoints(resultSet.getInt("POINTS"));
        this.setExerciseId1(resultSet.getInt("EXERCISE_ID_1"));
        this.setExerciseId1_duration(resultSet.getInt("EXERCISE_ID_1_DURATION")); // Updated to include exercise duration
        this.setExerciseId2(resultSet.getInt("EXERCISE_ID_2"));
        this.setExerciseId2_duration(resultSet.getInt("EXERCISE_ID_2_DURATION")); // Updated to include exercise duration
        this.setExerciseId3(resultSet.getInt("EXERCISE_ID_3"));
        this.setExerciseId3_duration(resultSet.getInt("EXERCISE_ID_3_DURATION")); // Updated to include exercise duration
    }

}
