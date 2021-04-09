package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import logic.ApplicationEvent;

public class MainController {

    @FXML
    private MenuItem prefItem;

    @FXML
    private MenuItem quitItem;

    @FXML
    private MenuItem queryCommandItem;

    @FXML
    private MenuItem creerClientItem;

    @FXML
    private MenuItem aboutItem;

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void oncreateClient(ActionEvent event) {
        Main.getAppC().showWindow(ApplicationEvent.appWindows.CREATE_CLIENT,true);
    }
}
