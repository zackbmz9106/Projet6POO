package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import logic.ApplicationEvent;
import magasin.Client;
import magasin.Produit;
import org.springframework.lang.Nullable;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CommandeController extends ShowHideDialog implements Initializable {


    @FXML
    private ListView<String> LcommandeView;

    private ObservableList<String> commandeList = FXCollections.observableArrayList();

    private ArrayList<Produit> productList = new ArrayList<Produit>();

    private Produit currentSelectedObj;


    @FXML
    private clientController PClientController;

    @FXML
    private TextField totalTArea;

    private Client currentClient;

    @FXML
    private TextField reductionField;

    private float reduction = 0;

    private float totalSansReduc = 0;

    @FXML
    private DatePicker dateLivraison;

    @FXML
    void onAllDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfimer ?");
        alert.setHeaderText("Voulez-vous vraiment tout supprimer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            commandeList = FXCollections.observableArrayList();
            productList = new ArrayList<Produit>();
            totalSansReduc=0;
            setPrice(0);
        } else {
            return ;
        }

    }

    @FXML
    void add(ActionEvent event) {
        Main.getAppEventDisp().showWindow(ApplicationEvent.appWindows.CREATE_PRODUIT_ADDER,true);
    }

    @FXML
    void onClientCreate(ActionEvent event) {
        Main.getAppEventDisp().showWindow(ApplicationEvent.appWindows.CREATE_CLIENT, true);
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
            setPrice(-currentSelectedObj.getPrixArticle());
            commandeList.remove(affichProduit(currentSelectedObj));
            productList.remove(currentSelectedObj);
            currentSelectedObj = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_COMMANDE);
        LcommandeView.setItems(commandeList);
        PClientController.setStandalone(false);
        PClientController.setForClientRead(false);
        Button ClientActionButton  = PClientController.getActionButton();
        ClientActionButton.setDisable(true);
        ClientActionButton.setVisible(false);
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case ADDED_PRODUIT:
                    Produit p = (Produit) params[0];
                    commandeList.add(affichProduit(p));
                    productList.add(p);
                    setPrice(p.getPrixReel());
                    break;
                case SELECTED_CLIENT:
                case NEW_CLIENT:
                    Client c = (Client) params[0];
                    currentClient = c;
                    PClientController.clientReadout(c);
                    break;
            }
        });
    }

    private void setPrice(float price){
        totalSansReduc += price;
        try {
            reduction = Float.parseFloat(reductionField.getText()) / 100;
        }catch (NumberFormatException e){
            showError("Reduction invalide");
            return;
        }
        totalTArea.setText(String.valueOf(totalSansReduc - (reduction*totalSansReduc)));
    }
    private String affichProduit(Produit p ){
        return p.getNomArticle();
    }

    @Override
    protected Window getWindow() {
        return LcommandeView.getScene().getWindow();
    }
}
