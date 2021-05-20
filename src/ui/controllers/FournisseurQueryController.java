package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.ApplicationEvent;
import magasin.DBObject;
import magasin.Fournisseur;
import ui.controllers.QueryBaseController;

import java.net.URL;
import java.util.ResourceBundle;

public class FournisseurQueryController extends QueryBaseController {

    @FXML
    FournisseurController PFournisseurControlleur;
    @Override
    protected Button getUpdateButton() {
        return null;
    }

    @Override
    protected Button getActionButton() {
        return PFournisseurControlleur.getActionButton();
    }

    @Override
    void setToInternPane(DBObject o) {
        PFournisseurControlleur.setToInternPane((Fournisseur)o);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_FOURNISSEUR_QUERY);
        PFournisseurControlleur.setForReadout(true);
        PFournisseurControlleur.setStandalone(false);
        getActionButton().setOnAction((ActionEvent) ->{
           removeCurrentObj();
        });
        getActionButton().setText("Supprimer");

    }

    public void onUpdate(ActionEvent actionEvent) {
    }
}
