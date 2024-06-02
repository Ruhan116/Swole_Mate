package com.example.swole_mate.Util;

import com.example.swole_mate.Database.ProgramDB;
import com.example.swole_mate.Main;
import com.example.swole_mate.controller.WorkoutPageController;
import com.example.swole_mate.model.Program;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProgramRunner {
    private Program program;

    public ProgramRunner(int prog) throws SQLException {
        ProgramDB programDB = new ProgramDB();
        program = ProgramDB.fetchProg(prog);
    }

    public void run(WorkoutPageController workoutPageController) throws IOException, SQLException {
        int exer1 = program.getExerciseId1();
        int exer2 = program.getExerciseId2();
        int exer3 = program.getExerciseId3();

        int exer1dur = program.getExerciseId1_duration();
        int exer2dur = program.getExerciseId2_duration();
        int exer3dur = program.getExerciseId3_duration();

        int[] exerlist = {exer1, exer2, exer3};
        int[] exerDurlist = {exer1dur, exer2dur, exer3dur};

        runAll(exerlist, exerDurlist, workoutPageController);

    }
    private void runAll(int[] exer , int[] dur, WorkoutPageController workoutPageController) throws IOException, SQLException {

        workoutPageController.run(exer, dur);


    }
}
