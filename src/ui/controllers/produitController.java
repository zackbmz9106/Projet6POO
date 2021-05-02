package ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import logic.ApplicationEvent;
import magasin.Produit;
import ui.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class produitController extends ShowHideDialog implements Initializable {

    @FXML
    private TextField Tstock;

    @FXML
    private Button myButton;

    @FXML
    private TextField TypeArticle;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Marque;

    @FXML
    private TextField Prix;

    @FXML
    private TextField Solde;

    @FXML
    private CheckBox Iss;

    @FXML
    private TextField TfourName;


    private long searchIDFournisseur() {
        String fourName = TfourName.getText().trim();
        if (fourName.equals("")) {
            showError("Entrée une nom de fournisseur valable");
            return -1;
        }
        long fourId = Main.getAppC().searchFournisseur(fourName);
        if (fourId < 0) {
            showError("Fournisseur introuvable");
            return -1;
        }
        return fourId;
    }

    @FXML
    void buttonClicked(ActionEvent event) {
        String typeArticle = TypeArticle.getText().trim();
        String nom = Nom.getText().trim();
        String marque = Marque.getText().trim();
        float prix;
        float solde = 0;
        long stock = 0 ;
        boolean iss = Iss.isSelected();
        try {
            prix = Float.parseFloat(Prix.getText().trim());
            stock = Long.parseLong(Tstock.getText().trim());
            if (iss) {
                solde = Float.parseFloat(Solde.getText().trim());
            }
        } catch (NumberFormatException e) {
            showError("Verifier les nombres");
            return;
        }
        if (typeArticle.equals("") || marque.equals("") || nom.equals("")) {
            showError("Les champs ne peuvent etre vide");
            return;
        }
        if (iss && solde <= 0) {
            showError("Verifier les soldes");
            return;
        }
        if (prix < 0) {
            showError("Verifier le prix");
            return;
        }
        if(stock <= 0){
            showError("Verifier le stock");
            return;
        }
        long IDFournissseur = searchIDFournisseur();
        if (IDFournissseur == -1) {
            showError("Fournisseur introuvable");
            return;
        }

        Main.getAppC().createProduit(typeArticle, marque, nom, prix, iss, solde, IDFournissseur,stock);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppDispatch(ApplicationEvent.appWindows.CREATE_PRODUIT);
    }

    @Override
    protected Window getWindow() {
        return TypeArticle.getScene().getWindow();
    }

    public void produitReadout(Produit p ){
        TypeArticle.setText(p.getTypeArticle());
        Nom.setText(p.getNomArticle());
        Marque.setText(p.getMarque());
        Prix.setText(String.valueOf(p.getPrixArticle()));
        float solde = p.getSolde();
        if(solde != 0.0) {
            Solde.setText(String.valueOf(solde));
        }else{
            Solde.setText("");
        }
        Iss.setSelected(p.isSolde());
        TfourName.setText(p.getFourName());
        long qty = Main.getAppC().searchQtyOfProduit(p.getId());
        Tstock.setText(String.valueOf(qty));
    }

    public void setForProduitRead(boolean b) {
        TypeArticle.setEditable(b);
        Nom.setEditable(b);
        Marque.setEditable(b);
        Prix.setEditable(b);
        Solde.setEditable(b);
        Iss.setDisable(b);
        TfourName.setEditable(b);
    }
    public Button getActionButton() {
        return myButton;
    }

    public void clean() {
        TypeArticle.setText("");
        Nom.setText("");
        Marque.setText("");
        Prix.setText("");
        Solde.setText("");
        Iss.setSelected(false);
        TfourName.setText("");
    }
}
