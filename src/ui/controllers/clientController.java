package ui.controllers;

import commons.Adresse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import logic.ApplicationEvent;
import magasin.Client;
import ui.Main;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class clientController extends ShowHideDialog{


    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @FXML
    private TextField villeText;
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

    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

    }

    private LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }


    public void setForClientRead(boolean b) {
        naissancePicker.setEditable(b);
        TNom.setEditable(b);
        TNumeroTel.setEditable(b);
        TPrenom.setEditable(b);
        TMail.setEditable(b);
        TAdresse.setEditable(b);
        TNvoie.setEditable(b);
        TCodePostal.setEditable(b);
        BFidel.setDisable(b);
        villeText.setEditable(b);
//        myButton.setVisible(b);
    }

    public Button getActionButton() {
        return myButton;
    }

    public void clientReadout(Client c) {
        naissancePicker.setValue(convertToLocalDateViaSqlDate(c.getDateDeNaissance()));
        TNom.setText(c.getNom());
        TNumeroTel.setText(c.getNumerotel());
        TPrenom.setText(c.getPrenom());
        TMail.setText(c.getMail());
        Adresse a = c.getAdresse();
        TAdresse.setText(a.getVoie());
        TNvoie.setText(a.getnVoie());
        TCodePostal.setText(a.getCodePostal());
        villeText.setText(a.getVille());
        BFidel.setSelected(c.isCarteFidelite());
    }

    public void clean() {
        naissancePicker.setValue(null);
        TNom.setText("");
        TNumeroTel.setText("");
        TPrenom.setText("");
        TMail.setText("");
        TAdresse.setText("");
        TNvoie.setText("");
        TCodePostal.setText("");
        villeText.setText("");
        BFidel.setSelected(false);
    }

    private boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    @FXML
    void clickonButton(MouseEvent event) {
        String nom = TNom.getText().trim();
        String prenom = TPrenom.getText().trim();
        LocalDate datanaissance = naissancePicker.getValue();
        String mail = TMail.getText().trim();
        String tel = TNumeroTel.getText().trim();
        boolean Fidel = BFidel.isSelected();
        String adresse = TAdresse.getText().trim();
        String nVoie = TNvoie.getText().trim();
        String codePostal = TCodePostal.getText().trim();
        String ville = villeText.getText().trim();
        if (!validate(mail) && mail.length() != 0) {
            showError("Adresse mail invalide");
            return;
        }
        if (nom.length() == 0 || prenom.length() == 0) {
            showError("Nom/Prenom invalide");
            return;
        }

        if (mail.length() == 0 && tel.length() == 0) {
            showError("Au moins le mail ou le téléphone doivent etre valide");
            return;
        }
        int nbphone = Main.getAppC().searchPhoneNumberClient(tel);
        if(nbphone>0 && nbphone!= -1 ){
            showError("Le numéro de téléphone existe deja");
            return;
        }
        if (adresse.length() == 0 || nVoie.length() == 0 || codePostal.length() == 0 || ville.length() == 0) {
            showError("Veuilliez verifier l'adresse");
            return;
        }
        Adresse a = new Adresse(adresse, nVoie, codePostal, ville);
        LocalDate ld = LocalDate.now();
        if(datanaissance==null || datanaissance.isAfter(ld)){
            showError("Date de naissance invalide");
            naissancePicker.requestFocus();
            return;
        }
        if(isStandalone) {
            Main.getAppC().createClient(prenom, nom, a, convertToDateViaInstant(datanaissance), mail, tel, Fidel);
        }else {
            Main.getAppC().updateClient(prenom, nom, a, convertToDateViaInstant(datanaissance), mail, tel, Fidel);
        }

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
