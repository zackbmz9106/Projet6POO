package logic;

import commons.Adresse;
import database.Transaction;
import javafx.scene.control.Alert;
import ui.Main;

import java.util.Date;

public class AppController  {


    public void createClient(String nom, String prenom, Adresse adresse, Date dateDeNaissance, String mail, String numerotel, boolean carteFidelite){

        Transaction tx = Main.getAppM().createClient(nom,prenom,adresse,dateDeNaissance,mail,numerotel,carteFidelite);
        if(tx.getLevel() != Alert.AlertType.NONE){
            String e = tx.getMessage();
            Alert alert = new Alert(tx.getLevel());
            alert.setTitle("Creation du client");
//            alert.setHeaderText("Erreur d'inscription dans la base de donnée");
            alert.setContentText(e);
            alert.showAndWait();
        }
    }
}
