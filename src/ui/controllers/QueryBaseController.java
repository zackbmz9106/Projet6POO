package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Window;
import magasin.DBObject;
import ui.Main;

public abstract class QueryBaseController extends ShowHideDialog implements Initializable {

    @FXML
    protected ListView<String> LElement;

    protected ObservableList<String> doList = FXCollections.observableArrayList();
    ;
    @FXML
    protected Text Tname;

    @FXML
    protected AnchorPane ASelectionData;

    protected ListView<String> NameList = new ListView<String>(doList);

    protected DBObject currentSelectedObj;

    @Override
    protected Window getWindow() {
        return this.Tname.getScene().getWindow();
    }

    @FXML
    abstract void onMouseClickedOnList(MouseEvent event);

    abstract void setToInternPane(DBObject o);

    void removeCurrentObj() {
        Main.getAppC().removeDBObject(currentSelectedObj);
    }

}
