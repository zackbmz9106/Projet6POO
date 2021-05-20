package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import logic.ApplicationEvent;
import magasin.Fournisseur;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FournisseurController extends ShowHideDialog{
    @FXML
    public Button actionButton;
    @FXML
    public TextField Tfourname;

    @Override
    protected Window getWindow() {
        return Tfourname.getScene().getWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_FOURNISSEUR);


    }

    public void onCreate(ActionEvent actionEvent) {
        String fourname = Tfourname.getText().trim();
        ArrayList<Fournisseur> results = Main.getAppC().searchAllFournisseurs();
        if(fourname.equals("")){
            showError("Nom invalide");
            return;
        }
        for(Fournisseur f : results){
            if(f.getNomFournisseur() == fourname){
                showError("Ce fournisseur existe deja");
                return;
            }
        }
        if(isStandalone) {
            Main.getAppC().createFournisseur(fourname);
        }else {
            Main.getAppC().updateFournisseur(fourname);
        }
    }

    public Button getActionButton() {
        return actionButton;
    }

    public void setForReadout(boolean b){
        Tfourname.setEditable(b);
    }
    public void setToInternPane(Fournisseur o) {
        Tfourname.setText(o.getNomFournisseur());
    }

    public void clean() {
        Tfourname.setText("");
    }
}
