package com.example.fluidsimv2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public int screenwidth = 500;

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        Scene scene = new Scene(root, screenwidth, screenwidth);
        stage.setTitle("Fluid Sim v2");
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();

        Platform.runLater(() -> {
            Controller controller = new Controller(scene);
            stage.setOnCloseRequest(windowEvent -> {System.exit(0);});
        });
    }

    public static void main(String[] args) {
        launch();
    }
}