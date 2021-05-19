package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import logic.ApplicationEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
    @FXML
    public ImageView imageViewPane;
    ArrayList<Image> imageGal = new ArrayList<Image>();
    //ecrire la description des image qui defille ne pas oublier les \n pour sauter des lignes
    String[] textList = {"1",
            "2",
    };
    private int Index;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_DEMO);
        back.setDisable(true);
        try {
            //ajouter les images ici avec leur nom
            imageGal.add(new Image(Objects.requireNonNull(DemoController.class.getResourceAsStream("./img/1.jpg"))));
            imageGal.add(new Image(Objects.requireNonNull(DemoController.class.getResourceAsStream("./img/2.jpg"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Index = 0;
        imgDescArea.setText(textList[Index]);
        imageViewPane.setImage(imageGal.get(Index));
        assert imageGal.size() == textList.length;
    }

    @Override
    protected Window getWindow() {
        return next.getScene().getWindow();
    }

    public void onAdvance(ActionEvent actionEvent) {
        if (Index + 1 < imageGal.size()) {
            Index++;
            imgDescArea.setText(textList[Index]);
            imageViewPane.setImage(imageGal.get(Index));
            next.setDisable((Index + 1 == imageGal.size()));
        }
        if (Index > 0) {
            back.setDisable(false);
        }
    }

    public void onBack(ActionEvent actionEvent) {
        if (Index > 0) {
            Index--;
            imgDescArea.setText(textList[Index]);
            imageViewPane.setImage(imageGal.get(Index));
            back.setDisable((Index == 0));
        }
        if (!(Index + 1 == imageGal.size())) {
            next.setDisable(false);
        }

    }
}
