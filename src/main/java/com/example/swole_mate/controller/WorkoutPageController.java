package com.example.swole_mate.controller;

import com.example.swole_mate.Database.ExerciseDB;
import com.example.swole_mate.Main;
import com.example.swole_mate.model.Exercise;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.example.swole_mate.Util.Clock;

public class WorkoutPageController implements Initializable {

    @FXML
    private VBox Main;

    @FXML
    private Label clockLabel;

    @FXML
    private Circle clockCircle;


    @FXML
    private Button nextButton;

    @FXML
    private MediaView mediaView;

    @FXML
    private Label nextExercise;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label thisExercise;

    @FXML
    private Button puaseButton;

    @FXML
    private Button skipButton;


    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    private int[] exercises;
    private int[] durations;

    private boolean finished;

    private int current;

    private boolean paused;

    private Clock myClock;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        finished = true;
        current =0;
        paused = false;
    }

    public int[] getExercises() {
        return exercises;
    }

    // Setter for exercises
    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    // Getter for durations
    public int[] getDurations() {
        return durations;
    }

    // Setter for durations
    public void setDurations(int[] durations) {
        this.durations = durations;
    }

    public boolean getFinished()
    {
        return finished;
    }

    public void setFinished(boolean x)
    {
        finished = x;
    }


    public void updateLabel(int minutes, int seconds)
    {
        myClock = new Clock(minutes, seconds, clockLabel);
        myClock.startCountdown();

        finished = myClock.getFinished();

        if(finished)
        {
            clockCircle.setVisible(false);
            nextButton.setVisible(true);
            nextButton.setDisable(false);

        }
    }

    public void run(int[] exer, int[] dur) throws SQLException {
        this.exercises = exer;
        this.durations = dur;

        update(exer, dur, 0);
    }

    public void update(int[] exer, int[] dur, int i) throws SQLException {
        System.out.println(current);

        updateLabel(dur[i],0);

        ExerciseDB exerciseDB = new ExerciseDB();

        List<Exercise> exerciseList = ExerciseDB.getExercisesByID(exer[i]);
        Exercise exercise = exerciseList.get(0);

        Exercise nextExer = null;
        if(i<2)
        {
            List<Exercise> nextExerciseList = ExerciseDB.getExercisesByID(exer[i+1]);
            nextExer = nextExerciseList.get(0);
        }


        thisExercise.setText(exercise.getName());
        if(i <2)
        {
            nextExercise.setText((nextExer.getName()));
        }
        else{
            nextExercise.setText("Finish");
        }

        file = new File("src/main/resources/com/example/swole_mate/view/workoutVideos/"+exer[i]+".mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        mediaView.setPreserveRatio(true);

        double fixedWidth = 500; // You can set this to any width you desire
        mediaView.setFitWidth(fixedWidth);

        mediaPlayer.setOnReady(() -> {
            // Wait until the media is ready and its metadata is available
            double mediaWidth = media.getWidth();
            double mediaHeight = media.getHeight();
            if (mediaHeight > 0) { // Prevent division by zero
                mediaView.fitHeightProperty().bind(
                        mediaView.fitWidthProperty().multiply(mediaHeight / mediaWidth)
                );
            }
        });

        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));


        mediaPlayer.play();


        ImageView pauseView = new ImageView(Main.class.getResource("view/icons/pause.png").toExternalForm());
        pauseView.setFitWidth(puaseButton.getWidth()); // Set the fitWidth to match the button's width
        pauseView.setFitHeight(puaseButton.getHeight()); // Set the fitHeight to match the button's height
        puaseButton.setGraphic(pauseView);

        ImageView skipView = new ImageView(Main.class.getResource("view/icons/skip.png").toExternalForm());
        skipView.setFitWidth(skipButton.getWidth()); // Set the fitWidth to match the button's width
        skipView.setFitHeight(skipButton.getHeight()); // Set the fitHeight to match the button's height
        skipButton.setGraphic(skipView);

        ImageView nextView = new ImageView(Main.class.getResource("view/icons/tick.png").toExternalForm());
        nextView.setFitWidth(nextButton.getWidth()); // Set the fitWidth to match the button's width
        nextView.setFitHeight(nextButton.getHeight()); // Set the fitHeight to match the button's height
        nextButton.setGraphic(nextView);


    }

    @FXML
    void nextButtonClicked(ActionEvent e) throws SQLException {
        myClock.startCountdown();
        if(current < 2)
        {
            current++;
            update( exercises, durations, current);
        }
        else
        {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/BMI.fxml"));
                Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                stage.setTitle("Swole-Mate");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    @FXML
    void goNext(ActionEvent e) throws SQLException {
        System.out.println(current);
        if(current < 2)
        {
            current++;
            update( exercises, durations, current);
        }
        else
        {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/pointsAdded.fxml"));
                Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                stage.setTitle("Swole-Mate");
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    @FXML
    void PauseAndContinue(ActionEvent event) {
        if(paused)
        {
            myClock.Continue();
            ImageView pauseView = new ImageView(Main.class.getResource("view/icons/pause.png").toExternalForm());
            pauseView.setFitWidth(puaseButton.getWidth()-17); // Set the fitWidth to match the button's width
            pauseView.setFitHeight(puaseButton.getHeight()-17); // Set the fitHeight to match the button's height
            puaseButton.setGraphic(pauseView);
            paused = false;
        }
        else{
            myClock.Pause();
            ImageView pauseView = new ImageView(Main.class.getResource("view/icons/continue.png").toExternalForm());
            pauseView.setFitWidth(puaseButton.getWidth()-17); // Set the fitWidth to match the button's width
            pauseView.setFitHeight(puaseButton.getHeight()-17); // Set the fitHeight to match the button's height
            puaseButton.setGraphic(pauseView);
            paused = true;
        }
    }

}
