package sample;

import commons.Adresse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import magasin.Client;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class clientController{
        private Adresse currentAdresse = new Adresse("Voie","route",1,"Ville",0 );
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
        private CheckBox BFidel;

        @FXML
        private TextField TFidel;

        @FXML
        private ProgressBar BProcessBar;

        private Date convertToDateViaInstant(LocalDate dateToConvert) {
            return java.util.Date.from(dateToConvert.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
        }
        @FXML
        void clickonButton(MouseEvent event) {
            Client c = null;
            try {
                c = new Client(TNom.getText(), TPrenom.getText(), currentAdresse
                        , convertToDateViaInstant(naissancePicker.getValue()), TMail.getText(),
                        Integer.parseInt(TNumeroTel.getText()),
                        BFidel.isSelected());
                BProcessBar.setProgress(50);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                BProcessBar.setProgress(70);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de Saisie");
                alert.setContentText("Veuilliez verifier que tous les champs sont corrects");
                alert.showAndWait();
            }
            if (c != null) {
                BProcessBar.setProgress(80);
                System.out.println(c.toString());

            }

            //BProcessBar.setProgress(0);
        }
}
