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

import java.util.ArrayList;

public abstract class QueryBaseController extends ShowHideDialog implements Initializable {


    protected ObservableList<String> doList = FXCollections.observableArrayList();

    @FXML
    protected ListView<String> LElement ;

    @FXML
    protected Text Tname;

    @FXML
    protected AnchorPane ASelectionData;

    protected  ArrayList<DBObject> dbObjects = new ArrayList<DBObject>();

    protected DBObject currentSelectedObj;

    @Override
    protected Window getWindow() {
        return this.Tname.getScene().getWindow();
    }

    @FXML
    void onMouseClickedOnList(MouseEvent event) {
        int index = LElement.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            currentSelectedObj = dbObjects.get(index);
            setToInternPane(currentSelectedObj);
        }
    }

    abstract void setToInternPane(DBObject o);

    void removeCurrentObj() {
        Main.getAppC().removeDBObject(currentSelectedObj);
    }

}
