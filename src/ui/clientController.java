package ui;

import commons.Adresse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import logic.ApplicationEvent;
import logic.IApplicationEventDispatcherListener;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class clientController extends ShowHideDialog implements Initializable {

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
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

    private boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private void showError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur de saisie");
        //alert.setHeaderText("Erreur d'inscription dans la base de donnée");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @FXML
    void clickonButton(MouseEvent event) {
        if (!validate(TMail.getText().trim())) {
            showError("Adresse mail invalide");
            return;
        }
        Adresse adresse = new Adresse(TAdresse.getText(), TNvoie.getText(), TCodePostal.getText());
        Main.getAppC().createClient(TNom.getText(), TPrenom.getText(), adresse, convertToDateViaInstant(naissancePicker.getValue()), TMail.getText(), TNumeroTel.getText(), BFidel.isSelected());

    }



    @Override
    protected Window getWindow() {
        return BFidel.getScene().getWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_CLIENT);
    }
}
