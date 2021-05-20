package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.ApplicationEvent;
import magasin.Client;
import magasin.DBObject;
import magasin.Fournisseur;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FournisseurQueryController extends QueryBaseController {

    @FXML
    FournisseurController PFournisseurController;
    @Override
    protected Button getUpdateButton() {
        return null;
    }

    @Override
    protected Button getActionButton() {
        return PFournisseurController.getActionButton();
    }

    @Override
    void setToInternPane(DBObject o) {
        PFournisseurController.setToInternPane((Fournisseur)o);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_FOURNISSEUR_QUERY);
        PFournisseurController.setForReadout(true);
        PFournisseurController.setStandalone(false);
        getActionButton().setOnAction((ActionEvent) ->{
           removeCurrentObj();
        });
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
                    switch (event) {
                        case NEW_FOURNISSEUR:
                            Fournisseur f = (Fournisseur) params[0];
                            dbObjects.add(f);
                            doList.add(f.getDesc());
                            break;
                        case FORCE_RELOAD:
                            InitialSearch();
                            break;
                        case DELETED:
                            DBObject dbo = (DBObject) params[0];
                            if (dbo.getClass().getSimpleName().equals("Fournisseur")) {
                                Fournisseur cl = (Fournisseur) dbo;
                                doList.remove(cl.getDesc());
                                dbObjects.remove(dbo);
                                if (currentSelectedObj != null && currentSelectedObj.equals(dbo)) {
                                    PFournisseurController.clean();
                                }
                            }
                    }
                });
        getActionButton().setText("Supprimer");
        InitialSearch();
        LElement.setItems(doList);

    }

    public void InitialSearch(){
        ArrayList<Fournisseur> fs = Main.getAppC().searchAllFournisseurs();
        for(Fournisseur f :fs) {
            doList.add(f.getDesc());
            dbObjects.add(f);
        }

    }
    public void onUpdate(ActionEvent actionEvent) {
        PFournisseurController.onCreate(null);
    }
}
