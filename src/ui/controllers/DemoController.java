package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import logic.ApplicationEvent;
import ui.Main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DemoController extends ShowHideDialog implements Initializable {
    @FXML
    public AnchorPane imagePane;
    @FXML
    public TextArea imgDescArea;
    @FXML
    public Button next;
    @FXML
    public Button back;

    ArrayList<ImageView> imageGal = new ArrayList<ImageView>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_DEMO);
        back.setDisable(true);
        try {
            String filePath = new File("").getAbsolutePath();
            imageGal.add(new ImageView(filePath + "/img/1.png"));
            imageGal.add(new ImageView(filePath + "/img/1.png"));
            }catch (Exception e){
            e.printStackTrace();
        }
        imagePane.getChildren().add(imageGal.get(0));

    }

    @Override
    protected Window getWindow() {
        return imagePane.getScene().getWindow();
    }

    public void onAdvance(ActionEvent actionEvent) {
    }

    public void onBack(ActionEvent actionEvent) {
    }
}
