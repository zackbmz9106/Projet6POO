package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logic.AppController;
import logic.AppModel;
import logic.ApplicationEvent;
import logic.ApplicationEventDispatcher;

import java.io.IOException;

public class Main extends Application {
    private static AppController appC;
    private static AppModel appM;

    public static ApplicationEventDispatcher getAppEventDisp() {
        return appEventDisp;
    }

    private static ApplicationEventDispatcher appEventDisp;

    public static Scene getPrimaryScene() {
        return primaryScene;
    }

    private static Scene primaryScene;

    public static AppController getAppC() {
        return appC;
    }

    public static AppModel getAppM() {
        return appM;
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Launching DB may take some time ...");
        appC = new AppController();
        appM = new AppModel();
        appEventDisp = new ApplicationEventDispatcher();
        appM.initDB();
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Project 6");
        primaryScene = new Scene(root, 590, 516);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        createShowHideDialog("./client.fxml","Client", ApplicationEvent.appWindows.CREATE_CLIENT);
        createShowHideDialog("./commande.fxml","Commande",ApplicationEvent.appWindows.CREATE_COMMANDE);
    }

    private void createShowHideDialog(String fxmlRessource, String title, ApplicationEvent.appWindows appWindow) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlRessource));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return;
        }

        Scene dlgScene = new Scene(root);

        Stage stage = new Stage();
//        stage.getIcons().add(APP_ICON);
        stage.setScene(dlgScene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle(title);
        stage.initModality(Modality.NONE);
        stage.initOwner(primaryScene.getWindow());
        stage.setOnCloseRequest((WindowEvent t) -> {
            appC.showWindow(appWindow, false);
            t.consume();
        });


        stage.show();
        stage.centerOnScreen();
        Main.getAppC().showWindow(appWindow, false);

    }

    @Override
    public void init() throws Exception {
        super.init();

    }
}
