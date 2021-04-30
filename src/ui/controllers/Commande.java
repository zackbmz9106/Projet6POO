package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import logic.ApplicationEvent;
import magasin.Client;
import magasin.Produit;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Commande implements Initializable {


    @FXML
    private ListView<String> LcommandeView;

    private ObservableList<String> commandeList = FXCollections.observableArrayList();

    private ArrayList<Produit> productList = new ArrayList<Produit>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LcommandeView.setItems(commandeList);
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case ADDED_PRODUIT:
                    Produit p = (Produit) params[0];
                    commandeList.add(affichProduit(p));
                    productList.add(p);
                    break;
            }
        });
    }
    private String affichProduit(Produit p ){
        return p.getNomArticle();
    }
}
