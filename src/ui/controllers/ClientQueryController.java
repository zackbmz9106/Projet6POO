package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.ApplicationEvent;
import magasin.Client;
import magasin.DBObject;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientQueryController extends QueryBaseController {

    @FXML
    private clientController PClientController;


    public Button getActionButton(){
        return PClientController.getActionButton();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_CLIENT_QUERY);
        PClientController.setForClientRead(false);
        PClientController.setStandalone(false);
        Button actionButton = PClientController.getActionButton();
        actionButton.setOnAction((ActionEvent) -> {
            removeCurrentObj();
        });
        actionButton.setText("Supprimer");
        launchInitialSearch();
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case NEW_CLIENT:
                    Client c = (Client) params[0];
                    doList.add(affichClient(c));
                    dbObjects.add(c);
                    break;
                case DELETED:
                    DBObject dbo = (DBObject) params[0];
                    if(dbo.getClass().getSimpleName().equals("Client")){
                        doList.remove(affichClient((Client) dbo));
                        dbObjects.remove(dbo);
                        if(currentSelectedObj.equals(dbo)){PClientController.clean();}
                    }
                    break;
                case FORCE_RELOAD:
                    launchInitialSearch();
            }
        });
        LElement.setItems(doList);
    }

    private void launchInitialSearch() {
        ArrayList<Client> results = new ArrayList<Client>();
        results = Main.getAppC().searchClient();
            dbObjects.addAll(results);
            for (DBObject c : dbObjects) {
                doList.add(affichClient((Client) c));
            }

    }

    private String affichClient(Client c) {
        return c.getNom() +" "+ c.getPrenom();
    }

    @Override
    void setToInternPane(DBObject o) {
        PClientController.clientReadout((Client) o);
    }


}
