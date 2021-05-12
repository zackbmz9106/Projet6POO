package ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import magasin.DBObject;
import ui.Main;

import java.util.ArrayList;

public abstract class QueryBaseController extends ShowHideDialog implements Initializable {


    protected ObservableList<String> doList = FXCollections.observableArrayList();

    @FXML
    protected ListView<String> LElement;

//    @FXML
//    protected Text Tname;

    @FXML
    protected AnchorPane ASelectionData;

    abstract protected Button getUpdateButton();
    protected ArrayList<DBObject> dbObjects = new ArrayList<DBObject>();
    protected DBObject currentSelectedObj;
    abstract protected Button getActionButton();


    public DBObject getCurrentSelectedObj() {
        return currentSelectedObj;
    }

    @Override
    protected Window getWindow() {
        return this.LElement.getScene().getWindow();
    }

    @FXML
    void onMouseClickedOnList(MouseEvent event) {
        int index = LElement.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            currentSelectedObj = dbObjects.get(index);
            setToInternPane(currentSelectedObj);
            getActionButton().setDisable(false);
            if(getUpdateButton() != null){
                getUpdateButton().setDisable(false);
            }

        }
    }

    abstract void setToInternPane(DBObject o);

    void removeCurrentObj() {
        Main.getAppC().removeDBObject(currentSelectedObj);
    }

}
