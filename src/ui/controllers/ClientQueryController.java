package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

    private ArrayList<Client> clientList;

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
                    break;
            }
        });
    }

    private void launchInitialSearch() {
        clientList = Main.getAppC().searchClient();
        for (Client c : clientList) {
            doList.add(affichClient(c));
        }

    }

    private String affichClient(Client c) {
        return c.getNom() + c.getPrenom();
    }

    @Override
    void onMouseClickedOnList(MouseEvent event) {
        int index = NameList.getSelectionModel().getSelectedIndex();
        if (index > 0) {
            currentSelectedObj = clientList.get(index);
            setToInternPane(currentSelectedObj);
        }
    }

    void setToInternPane(DBObject o) {
        PClientController.clientReadout((Client) o);
    }


}
