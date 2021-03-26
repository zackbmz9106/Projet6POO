package ui;

import database.DatabaseInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        DatabaseInterface DI = new DatabaseInterface();
        DI.lauchDatabase();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Project 6");
        primaryStage.setScene(new Scene(root, 590, 516));
        primaryStage.show();

    }

}
