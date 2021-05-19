package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import logic.ApplicationEvent;
import magasin.Client;
import magasin.DBObject;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientQueryController extends QueryBaseController {
    @FXML
    public CheckBox modifC;
    @FXML
    public Button updButton;
    @FXML
    private clientController PClientController;


    @Override
    protected Button getUpdateButton() {
        return updButton;
    }

    @Override
    public Button getActionButton() {
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
        actionButton.setDisable(true);
        updButton.setDisable(!modifC.isSelected());
        launchInitialSearch();
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case NEW_CLIENT:
                    Client c = (Client) params[0];
                    doList.add(c.getDesc());
                    dbObjects.add(c);
                    break;
                case DELETED:
                    DBObject dbo = (DBObject) params[0];
                    if (dbo.getClass().getSimpleName().equals("Client")) {
                        Client cl = (Client) dbo;
                        doList.remove(cl.getDesc());
                        dbObjects.remove(dbo);
                        if (currentSelectedObj != null && currentSelectedObj.equals(dbo)) {
                            PClientController.clean();
                        }
                    }
                    break;
                case FORCE_RELOAD:
                    launchInitialSearch();
                    break;
            }
        });
        LElement.setItems(doList);
    }

    private void launchInitialSearch() {
        ArrayList<Client> results = new ArrayList<Client>();
        results = Main.getAppC().searchAllClient();
        dbObjects.addAll(results);
        for (DBObject c : dbObjects) {
            Client cl = (Client) c;
            doList.add(cl.getDesc());
        }

    }

    @Override
    void setToInternPane(DBObject o) {
        PClientController.clientReadout((Client) o);
    }


    public void onUpdateButton(ActionEvent actionEvent) {
        if (modifC.isSelected()) {
            PClientController.clickonButton(null);
            currentSelectedObj = null;
        } else {
            return;
        }
    }

    public void onAuthorizeModif(ActionEvent actionEvent) {
        PClientController.setForClientRead(modifC.isSelected());
        if (currentSelectedObj != null) {
            updButton.setDisable(!modifC.isSelected());
        }
    }
}
