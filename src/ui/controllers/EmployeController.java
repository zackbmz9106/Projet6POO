package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import logic.ApplicationEvent;
import ui.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeController extends ShowHideDialog implements Initializable {


    @FXML
    private Button myButton;

    @FXML
    private TextField TNomEmploye;

    @FXML
    private TextField TNumEmploye;

    @FXML
    private TextField Ttype;

    boolean onNumberEmployeeExist(int numEmployee){
        return Main.getAppC().searchEmployeExist(numEmployee) == -1 ;
    }
    @FXML
    void buttonClicked(ActionEvent event) {
        String nom = TNomEmploye.getText().trim();
        int id = -1;
        String type = Ttype.getText().trim();
        try{
            id = Integer.parseInt(TNumEmploye.getText().trim());
        }catch(NumberFormatException e){
            showError("Verifier l'id");
            return;
        }
        if(nom.equals("")||type.equals("")){
            showError("Les champs ne peuvent etre vide");
            return;
        }
        if(!onNumberEmployeeExist(id)){
            showError("Ce numero d'employer est deja utilisé");
            return;
        }
        Main.getAppC().createEmploye(nom,id,type);
    }

    @Override
    protected Window getWindow() {
        return myButton.getScene().getWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_EMPLOYE);
    }
}
