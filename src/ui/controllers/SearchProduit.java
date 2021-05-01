package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import logic.ApplicationEvent;
import magasin.Produit;
import ui.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchProduit extends ShowHideDialog implements Initializable {

    private Produit selectedProduit ;
    @FXML
    private queryProduitController PQueryProduitController;
    @FXML
    private AnchorPane pane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_PRODUIT_ADDER);
        PQueryProduitController.setforadd(false);
        Button actionButton = PQueryProduitController.getActionButton();
        actionButton.setOnAction((ActionEvent)-> {
            selectedProduit = PQueryProduitController.getCurrentSelectedProduit();
            if(selectedProduit != null) {
                Main.getAppEventDisp().notifyAddedProduct(selectedProduit);
                hide();
            }
        });
        actionButton.setText("Ajouter");
    }

    @Override
    protected Window getWindow() {
        return pane.getScene().getWindow();
    }
}
