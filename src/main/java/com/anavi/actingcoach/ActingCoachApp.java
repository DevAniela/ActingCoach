package com.anavi.actingcoach;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ActingCoachApp extends Application {

    @Override
    public void init() {
        DatabaseManager.initializeDatabase();
        System.out.println("Database initialized.");
    }

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Welcome to Acting Coach!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Acting Coach");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
