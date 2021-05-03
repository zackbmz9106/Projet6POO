package ui.controllers;

import commons.Adresse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import logic.ApplicationEvent;
import magasin.Client;
import magasin.Commande;
import magasin.Produit;
import ui.Main;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class CommandeController extends ShowHideDialog implements Initializable {

    @FXML
    public TextField TAdresse;
    @FXML
    public TextField TCodePostal;
    @FXML
    public TextField villeText;
    @FXML
    public TextField paiementT;
    @FXML
    public Button allDeleteButton;
    @FXML
    public Button actionButton;
    @FXML
    public Button addButton;
    @FXML
    public Button createClientButton;
    @FXML
    public Button clientSearchButton;
    @FXML
    public Button selectedDeleteButton;
    @FXML
    private ListView<String> LcommandeView;

    private ObservableList<String> commandeList = FXCollections.observableArrayList();

    private ArrayList<Produit> productList = new ArrayList<Produit>();

    private Produit currentSelectedObj;

    @FXML
    private TextField TNvoie;

    @FXML
    private clientController PClientController;

    @FXML
    private TextField totalTArea;

    private Client currentClient;

    @FXML
    private TextField reductionField;

    private float reduction = 0;

    private float totalSansReduc = 0;

    @FXML
    private DatePicker dateLivraison;

    @FXML
    void onAllDelete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfimer ?");
        alert.setHeaderText("Voulez-vous vraiment tout supprimer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            commandeList = FXCollections.observableArrayList();
            productList = new ArrayList<Produit>();
            totalSansReduc = 0;
            setPrice(0);
        } else {
            return;
        }

    }

    @FXML
    void add(ActionEvent event) {
        Main.getAppEventDisp().showWindow(ApplicationEvent.appWindows.CREATE_PRODUIT_ADDER, true);
    }

    @FXML
    void onClientCreate(ActionEvent event) {
        Main.getAppEventDisp().showWindow(ApplicationEvent.appWindows.CREATE_CLIENT, true);
    }

    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

    }
    private LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
    public void CommandeReadout(Commande c){
        Adresse a = c.getAdresseLivr();
        TAdresse.setText(a.getVoie());
        TNvoie.setText(a.getnVoie());
        TCodePostal.setText(a.getCodePostal());
        villeText.setText(a.getVille());
        dateLivraison.setValue(convertToLocalDateViaSqlDate(c.getDateLivraison()));
        paiementT.setText(c.getTypePaiement());
        PClientController.clientReadout(Main.getAppC().searchClient(c.getID_client()));
    }
    @FXML
    void onContinue(ActionEvent event) {
        String adresse = TAdresse.getText().trim();
        String nVoie = TNvoie.getText().trim();
        String codePostal = TCodePostal.getText().trim();
        String ville = villeText.getText().trim();
        LocalDate dateLivr = dateLivraison.getValue();
        String paiemenet = paiementT.getText().trim();
        if(adresse.length() == 0|| nVoie.length() == 0 || codePostal.length() == 0 || ville.length() == 0){
            showError("Verifier l'adresse");
            return;
        }
        LocalDate ld =  LocalDate.now();
        if(dateLivr.isBefore(ld)){
            showError("Date de livraison invalide");
            return;
        }
        if(paiemenet.length() == 0){
            showError("Verifier le moyen de paiement");
            return;
        }
        if(currentClient == null){
            showError("Veuillez selectionner un client");
            return;
        }
        Adresse a = new Adresse(adresse,nVoie,codePostal,ville);
        ArrayList<Long> idProductList = new ArrayList<Long>();
        for(Produit p : productList){
            idProductList.add(p.getId());
        }
        Main.getAppC().createCommande(idProductList,reduction,paiemenet,a,convertToDateViaInstant(dateLivr),currentClient.getID());
    }

    @FXML
    void onSearchClient(ActionEvent event){
        Main.getAppEventDisp().showWindow(ApplicationEvent.appWindows.CREATE_CLIENT_COMMANDE_FILLER,true);
    }

    @FXML
    void onMouseClickedOnList(MouseEvent event) {
        int index = LcommandeView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            currentSelectedObj = productList.get(index);
        }
    }

    @FXML
    void onSelectedDelete(ActionEvent event) {
        if (currentSelectedObj != null) {
            setPrice(-currentSelectedObj.getPrixArticle());
            commandeList.remove(affichProduit(currentSelectedObj));
            productList.remove(currentSelectedObj);
            currentSelectedObj = null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reductionField.setText("0");
        initAppDispatch(ApplicationEvent.appWindows.CREATE_COMMANDE);
        LcommandeView.setItems(commandeList);
        PClientController.setStandalone(false);
        PClientController.setForClientRead(false);
        Button ClientActionButton = PClientController.getActionButton();
        ClientActionButton.setDisable(true);
        ClientActionButton.setVisible(false);
        Main.getAppEventDisp().addListener((ApplicationEvent.events event, Object... params) -> {
            switch (event) {
                case ADDED_PRODUIT:
                    Produit p = (Produit) params[0];
                    commandeList.add(affichProduit(p));
                    productList.add(p);
                    setPrice(p.getPrixReel());
                    break;
                case SELECTED_CLIENT:
                case NEW_CLIENT:
                    Client c = (Client) params[0];
                    currentClient = c;
                    PClientController.clientReadout(c);
                    break;
            }
        });
    }

    private void setPrice(float price) {
        totalSansReduc += price;
        try {
            reduction = Float.parseFloat(reductionField.getText()) / 100;
        } catch (NumberFormatException e) {
            showError("Reduction invalide");
            return;
        }
        totalTArea.setText(String.valueOf(totalSansReduc - (reduction * totalSansReduc)));
    }

    private String affichProduit(Produit p) {
        return p.getNomArticle();
    }

    @Override
    protected Window getWindow() {
        return LcommandeView.getScene().getWindow();
    }

    public Button getActionButton(){
        return actionButton;
    }
    public void setForReadout(boolean b){
        allDeleteButton.setVisible(b);
        selectedDeleteButton.setVisible(b);
        addButton.setVisible(b);
        clientSearchButton.setVisible(b);
        createClientButton.setVisible(b);
        TAdresse.setVisible(b);
        TNvoie.setVisible(b);
        TCodePostal.setVisible(b);
        villeText.setVisible(b);
        dateLivraison.setVisible(b);
        paiementT.setVisible(b);
        reductionField.setVisible(b);

    }
}
