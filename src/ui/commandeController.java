package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Window;
import logic.ApplicationEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class commandeController extends ShowHideDialog implements Initializable {

    @FXML
    private Button bCreate;

    @FXML
    void createCommande(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_COMMANDE);
    }

    @Override
    protected Window getWindow() {
        return bCreate.getScene().getWindow();
    }
}
