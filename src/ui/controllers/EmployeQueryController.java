package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.ApplicationEvent;
import magasin.DBObject;
import magasin.Employe;
import ui.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeQueryController extends QueryBaseController implements Initializable {


    @FXML
    private EmployeController PEmployeController;

    @Override
    protected Button getUpdateButton() {
        return null;
    }

    @Override
    protected Button getActionButton() {
        return PEmployeController.getActionButton();
    }

    @Override
    void setToInternPane(DBObject o) {
        PEmployeController.employeReadout((Employe) o);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_EMPLOYE_QUERY);
        PEmployeController.setStandalone(false);
        PEmployeController.setForReadout(false);
        Button actionButton = PEmployeController.getActionButton();
        actionButton.setText("Supprimer");
        actionButton.setOnAction((ActionEvent) -> {
            removeCurrentObj();
        });
        actionButton.setDisable(true);
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
                    switch (event) {
                        case NEW_EMPLOYE:
                            Employe e = (Employe) params[0];
                            doList.add(e.getDesc());
                            dbObjects.add(e);
                            break;
                        case DELETED:
                            DBObject dbo = (DBObject) params[0];
                            if (dbo.getClass().getSimpleName().equals("Employe")) {
                                Employe em = (Employe) dbo;
                                doList.remove(em.getDesc());
                                dbObjects.remove(em);
                                if (currentSelectedObj != null && currentSelectedObj.equals(em)) {
                                    PEmployeController.clean();
                                }
                            }
                            break;
                        case FORCE_RELOAD:
                            launchInitialSearch();
                            break;
                    }
                }

        );
        LElement.setItems(doList);
    }

    private void launchInitialSearch() {
        ArrayList<Employe> results = new ArrayList<Employe>();
        results = Main.getAppC().searchAllEmploye();
        dbObjects.addAll(results);
        for (DBObject c : dbObjects) {
            Employe e = (Employe) c;
            doList.add(e.getDesc());
        }
    }
}
