package com.example.swole_mate.Database;

import com.example.swole_mate.model.Exercise;
import com.example.swole_mate.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.swole_mate.Database.DatabaseManager.*;

public class ExerciseDB {


    public static void main(String[] args) throws SQLException {

        try {
            List<Exercise> exercisesByName = getExercisesByName("PushUp");
            for (Exercise exercise : exercisesByName) {
                System.out.println("Exercise: " + exercise.getName() + ", Difficulty: " + exercise.getDifficulty());
                System.out.println("Body Parts: " + exercise.getBodyParts());
            }

            List<Exercise> exercisesByBodyPart = getExercisesByBodyPart("Back");
            for (Exercise exercise : exercisesByBodyPart) {
                System.out.println("Exercise: " + exercise.getName() + ", Difficulty: " + exercise.getDifficulty());
                System.out.println("Body Parts: " + exercise.getBodyParts());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createExerciseTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS exercises (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "difficulty VARCHAR(255) NOT NULL)";

        execute_update(query);
    }

    public static void createBodyPartTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS body_parts (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL UNIQUE)";

        execute_update(query);
    }

    public static void createExerciseBodyPartsTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS exercise_body_parts (" +
                "exercise_id INT, " +
                "body_part_id INT, " +
                "FOREIGN KEY (exercise_id) REFERENCES exercises(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (body_part_id) REFERENCES body_parts(id), " +
                "PRIMARY KEY (exercise_id, body_part_id))";

        execute_update(query);
    }

    public static void insertExercise(Exercise exercise) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Start a transaction
            connection = getConnection("SWOLEMATE");
            connection.setAutoCommit(false);

            // Insert exercise
            String exerciseSQL = "INSERT INTO exercises (name, difficulty) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(exerciseSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, exercise.getName());
            preparedStatement.setString(2, exercise.getDifficulty());
            preparedStatement.executeUpdate();

            // Get the generated exercise ID
            rs = preparedStatement.getGeneratedKeys();
            int exerciseId = 0;
            if (rs.next()) {
                exerciseId = rs.getInt(1);
                System.out.println("Inserted exercise with ID: " + exerciseId);
            } else {
                throw new SQLException("Failed to retrieve exercise ID.");
            }

            // Insert body parts and link to exercise
            String bodyPartSQL = "INSERT INTO body_parts (name) VALUES (?) ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
            String linkSQL = "INSERT INTO exercise_body_parts (exercise_id, body_part_id) VALUES (?, ?)";
            for (String bodyPart : exercise.getBodyParts()) {
                // Insert body part
                preparedStatement = connection.prepareStatement(bodyPartSQL);
                preparedStatement.setString(1, bodyPart);
                preparedStatement.executeUpdate();

                // Fetch the body part ID
                String getBodyPartIDQuery = "SELECT id FROM body_parts WHERE name = ?";
                preparedStatement = connection.prepareStatement(getBodyPartIDQuery);
                preparedStatement.setString(1, bodyPart);
                rs = preparedStatement.executeQuery();
                int bodyPartId = 0;
                if (rs.next()) {
                    bodyPartId = rs.getInt(1);
                    System.out.println("Retrieved body part ID: " + bodyPartId + " for body part: " + bodyPart);
                } else {
                    throw new SQLException("Failed to retrieve body part ID for: " + bodyPart);
                }

                // Link exercise with body part
                preparedStatement = connection.prepareStatement(linkSQL);
                preparedStatement.setInt(1, exerciseId);
                preparedStatement.setInt(2, bodyPartId);
                preparedStatement.executeUpdate();
                System.out.println("Linked exercise ID " + exerciseId + " with body part ID: " + bodyPartId);
            }

            // Commit transaction
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }


    public static List<Exercise> getExercisesByName(String name) throws SQLException {
        List<Exercise> exercises = new ArrayList<>();
        String query = "SELECT e.id, e.name, e.difficulty FROM exercises e WHERE e.name LIKE ?";
        ResultSet rs = execute_query(query, "%" + name + "%");

        while (rs.next()) {
            int id = rs.getInt("id");
            String exerciseName = rs.getString("name");
            String difficulty = rs.getString("difficulty");

            Exercise exercise = new Exercise(id, exerciseName, difficulty);
            exercise.setBodyParts(getBodyPartsForExercise(id));
            exercises.add(exercise);
        }

        return exercises;
    }

    public static List<Exercise> getExercisesByID(int ID) throws SQLException {
        List<Exercise> exercises = new ArrayList<>();
        String query = "SELECT e.id, e.name, e.difficulty FROM exercises e WHERE e.id LIKE ?";
        ResultSet rs = execute_query(query, "%" + Integer.toString(ID) + "%");

        while (rs.next()) {
            int id = rs.getInt("id");
            String exerciseName = rs.getString("name");
            String difficulty = rs.getString("difficulty");

            Exercise exercise = new Exercise(id, exerciseName, difficulty);
            exercise.setBodyParts(getBodyPartsForExercise(id));
            exercises.add(exercise);
        }

        return exercises;
    }



    public static List<Exercise> getExercisesByBodyPart(String bodyPartName) throws SQLException {
        List<Exercise> exercises = new ArrayList<>();
        String query = "SELECT e.id, e.name, e.difficulty FROM exercises e " +
                "JOIN exercise_body_parts ebp ON e.id = ebp.exercise_id " +
                "JOIN body_parts bp ON ebp.body_part_id = bp.id " +
                "WHERE bp.name = ?";
        ResultSet rs = execute_query(query, bodyPartName);

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String difficulty = rs.getString("difficulty");

            Exercise exercise = new Exercise(id, name, difficulty);
            exercise.setBodyParts(getBodyPartsForExercise(id));
            exercises.add(exercise);
        }

        return exercises;
    }



    public static List<String> getBodyPartsForExercise(int exerciseId) throws SQLException {
        List<String> bodyParts = new ArrayList<>();
        String query = "SELECT bp.name FROM body_parts bp " +
                "JOIN exercise_body_parts ebp ON bp.id = ebp.body_part_id " +
                "WHERE ebp.exercise_id = ?";
        ResultSet rs = execute_query(query, exerciseId);

        while (rs.next()) {
            bodyParts.add(rs.getString("name"));
        }

        return bodyParts;
    }








}
