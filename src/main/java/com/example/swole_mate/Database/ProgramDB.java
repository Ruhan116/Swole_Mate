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


    public ProgramDB()
    {}

    public static void main(String[] args) throws SQLException {

        createProgramTable();

        System.out.println(fetchProg(1).getType());
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

    public static Program fetchProg(int prog) throws SQLException {

        String tableName = "PROGRAMS";
        Program program = new Program();
        String query = "SELECT * FROM " + tableName + " WHERE ID = '" + Integer.toString(prog) +"';";

        try{
            ResultSet resultSet = execute_query(query);
            while (resultSet.next()) {
                program.mapResultSetToProgram(resultSet);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return program;
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
