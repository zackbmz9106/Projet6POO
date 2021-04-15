package logic;

import commons.Adresse;
import database.Transaction;
import javafx.scene.control.Alert;
import magasin.Client;
import magasin.Employe;
import magasin.Produit;
import ui.Main;

import java.util.ArrayList;
import java.util.Date;

public class AppController {


    public void createClient(String nom, String prenom, Adresse adresse, Date dateDeNaissance, String mail, String numerotel, boolean carteFidelite) {

        Transaction tx = Main.getAppM().createClient(nom, prenom, adresse, dateDeNaissance, mail, numerotel, carteFidelite);
        if (tx.getLevel() != Alert.AlertType.NONE) {
            String e = tx.getMessage();
            Alert alert = new Alert(tx.getLevel());
            alert.setTitle("Creation du client");
//            alert.setHeaderText("Erreur d'inscription dans la base de donnée");
            alert.setContentText(e);
            alert.showAndWait();
            if (tx.getLevel() != Alert.AlertType.ERROR) {
                notifyNewClient((Client) tx.getCreatedObj());
            }
        }
    }

    public void createProduit(String typeArticle, String marque, String nomArticle, float prixArticle, boolean isSolde, float solde, long ID_fournisseur) {
        Transaction tx = Main.getAppM().createProduit(typeArticle, marque, nomArticle, prixArticle, isSolde, solde, ID_fournisseur);
        if (tx.getLevel() != Alert.AlertType.NONE) {
            String e = tx.getMessage();
            Alert alert = new Alert(tx.getLevel());
            alert.setTitle("Creation produit");
            alert.setContentText(e);
            alert.showAndWait();
            if (tx.getLevel() != Alert.AlertType.ERROR) {
                notifyNewProduit((Produit) tx.getCreatedObj());
            }
        }

    }


    public long searchFournisseur(String fourName) {
        Transaction tx = Main.getAppM().searchFournisseur(fourName);
        if (tx.getCreatedObj() != null) {
            ArrayList<Long> results = (ArrayList<Long>) tx.getCreatedObj();
            if (results.size() != 1) {
                return -1;
            } else {
                return results.get(0);
            }
        } else {
            return -1;
        }
    }

    public long searchEmployeExist(int numEmployee){
        Transaction tx = Main.getAppM().searchEmployeExist(numEmployee);
        if(tx.getCreatedObj() != null){
            ArrayList<Long> results = (ArrayList<Long>) tx.getCreatedObj();
            if(results.size() > 0) {
                return results.get(0);
            }else{
                return -1;
            }
        }else{
            return -1 ;
        }
    }

    public void createEmploye(String nomEmploye,int numEmploye,String typePoste) {
        Transaction tx = Main.getAppM().createEmploye(nomEmploye, numEmploye, typePoste);
        if (tx.getLevel() != Alert.AlertType.NONE) {
            String e = tx.getMessage();
            Alert alert = new Alert(tx.getLevel());
            alert.setTitle("Creation employe");
            alert.setContentText(e);
            alert.showAndWait();
            if (tx.getLevel() != Alert.AlertType.ERROR) {
                notifyNewEmploye((Employe) tx.getCreatedObj());
            }
        }
    }

    public void showWindow(ApplicationEvent.appWindows appWindow, boolean b) {
        Main.getAppEventDisp().showWindow(appWindow, b);
    }

    public void notifyNewClient(Client c) {
        Main.getAppEventDisp().notifyNewClient(c);
    }

    public void notifyNewProduit(Produit p) {
        Main.getAppEventDisp().notifyNewProduit(p);
    }

    public  void notifyNewEmploye(Employe e) {
        Main.getAppEventDisp().notifyNewEmploye(e);
    }
    }
