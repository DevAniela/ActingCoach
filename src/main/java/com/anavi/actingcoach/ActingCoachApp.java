package com.anavi.actingcoach;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ActingCoachApp extends Application {

    @Override
    public void init() {
        DatabaseManager.initializeDatabase();
        System.out.println("Database initialized.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/com/anavi/actingcoach/ui/fxml/CharacterSheetsView.fxml"));

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Acting Coach - Character Sheets");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
