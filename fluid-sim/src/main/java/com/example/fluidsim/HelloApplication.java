package com.example.fluidsim;

import com.example.fluidsim.Math.Particle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {
    Timer timer = new Timer();
    public int screenwidth = 500;


//    public Particle[] particles = new Particle[1];
//    particles[0] = new Particle(250,250,10,Color.RED);


    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root, screenwidth, screenwidth);
        scene.setFill(Color.BLACK);
        stage.setTitle("Fluid Sim");
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();

        Platform.runLater(() -> {
            Controller controller = new Controller(scene);

            stage.setOnCloseRequest(windowEvent -> {
                System.exit(0);
            });
        });

    }

    public static void main(String[] args) {
        launch();
    }
}