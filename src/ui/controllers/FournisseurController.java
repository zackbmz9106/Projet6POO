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
        if(fourname.equals("")){
            showError("Nom invalide");
            return;
        }
        Main.getAppC().createFournisseur(fourname);
    }

    public Button getActionButton() {
        return actionButton;
    }

    public void setForReadout(boolean b){
        Tfourname.setDisable(b);
    }
    public void setToInternPane(Fournisseur o) {
        Tfourname.setText(o.getNomFournisseur());
    }
}
