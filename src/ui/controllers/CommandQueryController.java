package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import logic.ApplicationEvent;
import magasin.Commande;
import magasin.DBObject;
import ui.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class CommandQueryController extends QueryBaseController implements Initializable {

    @FXML
    private CommandeController PCommandeController;
    @Override
    void setToInternPane(DBObject o) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_COMMANDE_QUERY);
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event){
                case NEW_COMMAND:
                    Commande c = (Commande) params[0];

            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
