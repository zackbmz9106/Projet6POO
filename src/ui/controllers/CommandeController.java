package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.ApplicationEvent;
import magasin.Client;
import magasin.Produit;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {


    @FXML
    private ListView<String> LcommandeView;

    private ObservableList<String> commandeList = FXCollections.observableArrayList();

    private ArrayList<Produit> productList = new ArrayList<Produit>();

    private Produit currentSelectedObj;

    private long total = 0;

    @FXML
    private TextField totalTArea;

    @FXML
    void onAllDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfimer ?");
        alert.setHeaderText("Voulez-vous vraiment tout supprimer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            commandeList = FXCollections.observableArrayList();
            productList = new ArrayList<Produit>();
        } else {
            return ;
        }

    }

    @FXML
    void add(ActionEvent event) {
        Main.getAppEventDisp().showWindow(ApplicationEvent.appWindows.CREATE_PRODUIT_ADDER,true);
    }

    @FXML
    void onContinue(ActionEvent event) {
//        Main.getAppC().createCommande();
    }

    @FXML
    void onMouseClickedOnList(MouseEvent event) {
        int index = LcommandeView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            currentSelectedObj = productList.get(index);
        }
    }

    @FXML
    void onSelectedDelete(ActionEvent event) {
        if(currentSelectedObj != null) {
            commandeList.remove(affichProduit(currentSelectedObj));
            productList.remove(currentSelectedObj);
            currentSelectedObj = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LcommandeView.setItems(commandeList);
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case ADDED_PRODUIT:
                    Produit p = (Produit) params[0];
                    commandeList.add(affichProduit(p));
                    productList.add(p);
                    total += p.getPrixReel();
                    totalTArea.setText(String.valueOf(total));
                    break;
            }
        });
    }
    private String affichProduit(Produit p ){
        return p.getNomArticle();
    }
}
