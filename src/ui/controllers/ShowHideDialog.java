package ui.controllers;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;
import logic.ApplicationEvent;
import ui.Main;

public abstract class ShowHideDialog {

    private ApplicationEvent.appWindows appWindow;

    protected abstract Window getWindow();

    protected void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur de saisie");
        //alert.setHeaderText("Erreur d'inscription dans la base de donnée");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    private void hide() {
        Stage stage = (Stage) getWindow();
        stage.hide();
    }

    private void show() {
        Stage stage = (Stage) getWindow();
        if (stage.isShowing()) {
            return;
        }
        stage.show();
    }

    protected void initAppDispatch(ApplicationEvent.appWindows appWindow) {
        this.appWindow = appWindow;
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case SHOW_WINDOW:
                    ApplicationEvent.appWindows window = (ApplicationEvent.appWindows) params[0];
                    Boolean bShow = (Boolean) params[1];
                    if (window == ShowHideDialog.this.appWindow) {
                        if (bShow) {
                            show();
                        } else {
                            hide();
                        }
                    }
                    break;
            }
        });

    }

}
