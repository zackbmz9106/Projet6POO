package ui;

import database.DatabaseInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.AppController;
import logic.AppModel;

public class Main extends Application {
    private AppController mainController;
    private  static DatabaseInterface dBi;
    private static AppController appC;
    private static AppModel appM;

    public static AppController getAppC() {
        return appC;
    }

    public static AppModel getAppM() {
        return appM;
    }

    public static void main(String[] args)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        appC = new AppController();
        appM = new AppModel();
        appM.initDB();
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Project 6");
        primaryStage.setScene(new Scene(root, 590, 516));
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();

    }
}
