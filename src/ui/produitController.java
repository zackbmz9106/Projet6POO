package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import logic.ApplicationEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class produitController extends ShowHideDialog implements Initializable {

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
    private ProgressBar BProcessBar;

    @FXML
    void buttonClicked(ActionEvent event) {
        String typeArticle ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_PRODUIT);
    }

    @Override
    protected Window getWindow() {
        return myButton.getScene().getWindow();
    }
}
