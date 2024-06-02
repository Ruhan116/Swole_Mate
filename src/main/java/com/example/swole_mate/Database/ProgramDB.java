package com.example.swole_mate.Database;

import com.example.swole_mate.model.Program;
import com.example.swole_mate.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.swole_mate.Database.DatabaseManager.execute_query;
import static com.example.swole_mate.Database.DatabaseManager.execute_update;

public class ProgramDB {

    public static void main(String[] args) throws SQLException {

        createProgramTable();

        addProgram("Cardio", "Easy", "30", 3, 200, 1, 10, 2, 15, 3, 20);
        addProgram("Cardio", "Medium", "40", 3, 300, 2, 10, 3, 15, 4, 20);
        addProgram("Cardio", "Hard", "50", 3, 400, 3, 10, 4, 15, 5, 20);
        addProgram("Weight Training", "Easy", "30", 3, 300, 4, 10, 5, 15, 1, 20);
        addProgram("Weight Training", "Medium", "40", 3, 400, 5, 10, 1, 15, 2, 20);
        addProgram("Weight Training", "Hard", "50", 3, 500, 1, 10, 2, 15, 3, 20);
        addProgram("Calisthenics", "Easy", "40", 3, 400, 2, 10, 3, 15, 4, 20);
        addProgram("Calisthenics", "Medium", "50", 3, 500, 3, 10, 4, 15, 5, 20);
        addProgram("Calisthenics", "Hard", "60", 3, 600, 4, 10, 5, 15, 1, 20);
    }

    public static void createProgramTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS PROGRAMS (" +
                "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                "TYPE VARCHAR(50) NOT NULL, " +
                "DIFFICULTY VARCHAR(50) NOT NULL, " +
                "DURATION VARCHAR(50) NOT NULL, " +
                "WORKOUT_NUMBERS INT NOT NULL, " +
                "POINTS INT NOT NULL, " +
                "EXERCISE_ID_1 INT NOT NULL, " +
                "EXERCISE_ID_1_DURATION INT NOT NULL, " +
                "EXERCISE_ID_2 INT NOT NULL, " +
                "EXERCISE_ID_2_DURATION INT NOT NULL, " +
                "EXERCISE_ID_3 INT NOT NULL, " +
                "EXERCISE_ID_3_DURATION INT NOT NULL" +
                ")";
        execute_update(query);
    }


    public static List<Program> fetchAll() throws SQLException {

        String tableName = "PROGRAMS";
        List<Program> programList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try{
            ResultSet resultSet = execute_query(query);

            while(resultSet.next())
            {
                Program program = new Program();
                program.mapResultSetToProgram(resultSet);
                programList.add(program);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return programList;
    }

    public static void addProgram(String type, String difficulty, String duration, int workoutNumbers, int points,
                                  int exerciseId1, int exerciseId1_duration, int exerciseId2, int exerciseId2_duration,
                                  int exerciseId3, int exerciseId3_duration) throws SQLException {
        String query = "INSERT INTO PROGRAMS (TYPE, DIFFICULTY, DURATION, WORKOUT_NUMBERS, POINTS, " +
                "EXERCISE_ID_1, EXERCISE_ID_1_DURATION, EXERCISE_ID_2, EXERCISE_ID_2_DURATION, " +
                "EXERCISE_ID_3, EXERCISE_ID_3_DURATION) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        execute_update(query, type, difficulty, duration, workoutNumbers, points,
                exerciseId1, exerciseId1_duration, exerciseId2, exerciseId2_duration,
                exerciseId3, exerciseId3_duration);
        System.out.println("Program successfully added");
    }
}
