package ui;

import javafx.stage.Stage;
import javafx.stage.Window;
import logic.ApplicationEvent;
import logic.ApplicationEventDispatcher;

public abstract class ShowHideDialog {

    private ApplicationEvent.appWindows appWindow;
    protected abstract Window getWindow();

    private void hide() {
        Stage stage = (Stage)getWindow();
        stage.hide();
    }

    private void show() {
        Stage stage = (Stage)getWindow();
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
