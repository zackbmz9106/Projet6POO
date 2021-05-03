package ui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import logic.ApplicationEvent;
import ui.Main;

public class MainController {

    @FXML
    private AnchorPane cartPane;
    @FXML
    private MenuItem queryProduitItem;

    @FXML
    private MenuItem prefItem;

    @FXML
    private MenuItem creerProduitItem;

    @FXML
    private MenuItem quitItem;

    @FXML
    private MenuItem queryCommandItem;

    @FXML
    private MenuItem creerClientItem;


    @FXML
    private MenuItem creerEmployeItem;

    @FXML
    private MenuItem aboutItem;

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void oncreateClient(ActionEvent event) {
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_CLIENT, true);
    }

    @FXML
    void oncreateProduit(ActionEvent event) {
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_PRODUIT, true);
    }

    @FXML
    void oncreateEmploye(ActionEvent event) {
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_EMPLOYE, true);
    }

    @FXML
    void OnRechercherClient(ActionEvent event) {
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_CLIENT_QUERY, true);
    }

    @FXML
    void onClientGraph(ActionEvent event) {
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_CLIENT_CHART, true);
    }

    @FXML
    void onLoadSqlSample(ActionEvent event) {
         loadSample ls =  new loadSample(Main.getAppM().getdBi());
    }

    @FXML
    void onqueryProduit(ActionEvent event) {
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_PRODUIT_QUERY,true);
    }


    @FXML
    void onCreateCommande(ActionEvent event) {
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_COMMANDE,true);
    }

    @FXML
    void onCreateAbout(ActionEvent event){
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_ABOUT,true);
    }
}
