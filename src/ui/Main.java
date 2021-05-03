package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logic.AppController;
import logic.AppModel;
import logic.ApplicationEvent;
import logic.ApplicationEventDispatcher;
import magasin.Stock;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import ui.controllers.ClientQueryController;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static final int MAJORVERSION = 0;
    public static final int VERSION = 1;
    public static final int BUILDNUMBER = 1;
    public static HardwareAbstractionLayer hal;
    public static SystemInfo si;


    public static Stock getStock() {
        return stock;
    }

    private static Stock stock = new Stock();
    private static AppController appC;
    private static AppModel appM;
    private static ApplicationEventDispatcher appEventDisp;
    private static Scene primaryScene;
    private ClientQueryController CQC = new ClientQueryController();
    private final Image APP_ICON = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/ui/comp.png")));

    public static ApplicationEventDispatcher getAppEventDisp() {
        return appEventDisp;
    }

    public static Scene getPrimaryScene() {
        return primaryScene;
    }

    public static AppController getAppC() {
        return appC;
    }

    public static AppModel getAppM() {
        return appM;
    }

    public static void main(String[] args) {
        si = new SystemInfo();
        hal = si.getHardware();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./fxml/mainWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        primaryStage.setTitle("Project 6");
        primaryScene = new Scene(root, 590, 516);
        primaryStage.setScene(primaryScene);
        primaryStage.getIcons().add(APP_ICON);
        primaryStage.show();
        primaryStage.setMaximized(true);

        createShowHideDialog("./fxml/client.fxml", "Client", ApplicationEvent.appWindows.CREATE_CLIENT);
        createShowHideDialog("./fxml/produit.fxml", "Produit", ApplicationEvent.appWindows.CREATE_PRODUIT);
        createShowHideDialog("./fxml/employe.fxml","Employe",ApplicationEvent.appWindows.CREATE_EMPLOYE);
        createShowHideDialog("./fxml/queryClient.fxml","Liste des clients", ApplicationEvent.appWindows.CREATE_CLIENT_QUERY);
        createShowHideDialog("./fxml/chartClient.fxml","Graphique des clients",ApplicationEvent.appWindows.CREATE_CLIENT_CHART);
        createShowHideDialog("./fxml/stockGestion.fxml","Gestion du stock", ApplicationEvent.appWindows.CREATE_PRODUIT_QUERY);
        createShowHideDialog("./fxml/searchProduit.fxml","Selection un produit a ajouter au panier", ApplicationEvent.appWindows.CREATE_PRODUIT_ADDER);
        createShowHideDialog("./fxml/Commande.fxml","Creation d'une commande",ApplicationEvent.appWindows.CREATE_COMMANDE);
        createShowHideDialog("./fxml/about.fxml","A propos",ApplicationEvent.appWindows.CREATE_ABOUT);
    }

    private void createShowHideDialog(String fxmlRessource, String title, ApplicationEvent.appWindows appWindow) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlRessource));
        Parent root;
        try {
            root = loader.load();
        } catch (Exception ex) {
            System.err.println("The following error occur while loading fxml : " + ex.getMessage());
            System.err.println(ex.getCause().toString());
            return;
        }

        Scene dlgScene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(APP_ICON);
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
