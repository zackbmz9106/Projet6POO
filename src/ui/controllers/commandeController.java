package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import logic.ApplicationEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class commandeController extends ShowHideDialog implements Initializable {


    @FXML
    private Button myButton;

    @FXML
    private TextField TypeArticle;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Marque;

    @FXML
    private TextField Prix;

    @FXML
    private TextField Solde;

    @FXML
    private CheckBox Iss;

    @FXML
    void clickonButton(ActionEvent event) {
/*        String typeArticle = TypeArticle.getText().trim();
        String nomArticle = Nom.getText().trim();
        String marque = Marque.getText().trim();
        String prix = Prix.getText().trim();
        String solde = */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_COMMANDE);
    }

    protected Window getWindow() {
        return myButton.getScene().getWindow();
    }
}
