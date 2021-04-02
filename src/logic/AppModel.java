package logic;

import commons.Adresse;
import database.DatabaseInterface;
import javafx.scene.control.Alert;
import magasin.Client;

import java.util.Date;

public class AppModel {
    private DatabaseInterface dBi;

    public void initDB(){
        dBi = new DatabaseInterface();
        dBi.lauchDatabase();
    }

    public void createClient(String nom, String prenom, Adresse adresse, Date dateDeNaissance, String mail, int numerotel, boolean carteFidelite){
        Client c = new Client(nom,prenom,adresse,dateDeNaissance,mail,numerotel,carteFidelite);
        boolean status = c.create(objt);
        if(!status){
            String e = objt.getLastErrorMessage();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur d'inscription dans la base de donnée");
            alert.setContentText("l'erreur suivante est survenue : " + e);
            alert.showAndWait();
        }
    }
}
