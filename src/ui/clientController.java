package ui;

import commons.Adresse;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class clientController  {

    @FXML
    private Button myButton;

    @FXML
    private DatePicker naissancePicker;

    @FXML
    private TextField TNom;

    @FXML
    private TextField TNumeroTel;

    @FXML
    private TextField TPrenom;

    @FXML
    private TextField TMail;

    @FXML
    private TextField TAdresse;

    @FXML
    private TextField TNvoie;

    @FXML
    private TextField TCodePostal;

    @FXML
    private CheckBox BFidel;


    @FXML
    private ProgressBar BProcessBar;

    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


    @FXML
    void clickonButton(MouseEvent event) {
        Adresse adresse = new Adresse(TAdresse.getText(),TNvoie.getText(),TCodePostal.getText());
        Main.getAppC().createClient(TNom.getText(),TPrenom.getText(),adresse,convertToDateViaInstant(naissancePicker.getValue()),TMail.getText(),TNumeroTel.getText(),BFidel.isSelected());

    }
}
