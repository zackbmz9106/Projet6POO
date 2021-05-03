package logic;

import commons.Adresse;
import database.Transaction;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import magasin.*;
import ui.Main;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class AppController {

    private void showDialog(Transaction tx , String name){
        Alert alert = new Alert(tx.getLevel());
        alert.setTitle(name);
        alert.setHeaderText(tx.getMessage());

        Exception ex = tx.getEx();
        if(ex != null) {
            ex.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            alert.getDialogPane().setExpandableContent(expContent);
        }
        alert.showAndWait();
    }


    public void createClient(String nom, String prenom, Adresse adresse, Date dateDeNaissance, String mail, String numerotel, boolean carteFidelite) {

        Transaction tx = Main.getAppM().createClient(nom, prenom, adresse, dateDeNaissance, mail, numerotel, carteFidelite);
        if (tx.getLevel() != Alert.AlertType.NONE) {
            showDialog(tx,"Creation client");
            if (tx.getLevel() != Alert.AlertType.ERROR) {
                notifyNewClient((Client) tx.getCreatedObj());
            }
        }
    }

    public void createProduit(String typeArticle, String marque, String nomArticle, float prixArticle, boolean isSolde, float solde, long ID_fournisseur,long qty) {
        Transaction tx = Main.getAppM().createProduit(typeArticle, marque, nomArticle, prixArticle, isSolde, solde, ID_fournisseur);
        if (tx.getLevel() != Alert.AlertType.NONE) {
            Main.getStock().setProduit((Produit) tx.getCreatedObj(), qty);
            Transaction stocktx = new Transaction(tx.getdBi());
            Main.getStock().create(stocktx);
            if (stocktx.getLevel() != Alert.AlertType.NONE && stocktx.getLevel() != Alert.AlertType.ERROR) {
                showDialog(tx, "Creation Produit");
                if (tx.getLevel() != Alert.AlertType.ERROR) {
                    notifyNewProduit((Produit) tx.getCreatedObj());
                }
            }else{
                showDialog(stocktx, "Stock");
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
            showDialog(tx,"Creation Employe");
            if (tx.getLevel() != Alert.AlertType.ERROR) {
                notifyNewEmploye((Employe) tx.getCreatedObj());
            }
        }
    }
    public void removeDBObject(DBObject o){
        Transaction tx = Main.getAppM().deleteDBObject(o);
        if (tx.getLevel() != Alert.AlertType.NONE) {
            showDialog(tx,"Suppression");
            if (tx.getLevel() != Alert.AlertType.ERROR) {
                notifyDeletedDBObject((DBObject) tx.getCreatedObj());
            }
        }

    }

    public ArrayList<Client> searchClient(){
        Transaction tx = Main.getAppM().searchAll("Client");
        if (tx.getLevel() != Alert.AlertType.NONE && tx.getLevel()!= Alert.AlertType.ERROR) {
            ArrayList<Client> out = new ArrayList<Client>();
            ArrayList<Long> objsid = (ArrayList<Long>) tx.getCreatedObj();
            for(long l : objsid){
                Client loadedClient = new Client();
                loadedClient.load(tx,l);
                if(tx.getLevel() != Alert.AlertType.ERROR){
                 out.add(loadedClient);
                }else {
                    System.err.println("Client avec id : " + l + " introuvable");
                }

            }
            return out;
        }else {
            ArrayList<Client> cl = new ArrayList<>();
            return cl;
        }
        }


    private void notifyDeletedDBObject(DBObject deletedObject) {
        Main.getAppEventDisp().notifyDeletedObj(deletedObject);
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
    public void notifyNewCommande(Commande c){
        Main.getAppEventDisp().notifyNewCommande(c);
    }
    public ArrayList<Produit> searchProduits() {
        Transaction tx = Main.getAppM().searchAll("Produit");
        if (tx.getLevel() != Alert.AlertType.NONE && tx.getLevel()!= Alert.AlertType.ERROR) {
            ArrayList<Produit> out = new ArrayList<Produit>();
            ArrayList<Long> objsid = (ArrayList<Long>) tx.getCreatedObj();
            for(long l : objsid){
                Produit loadedClient = new Produit();
                loadedClient.load(tx,l);
                if(tx.getLevel() != Alert.AlertType.ERROR){
                    out.add(loadedClient);
                }else {
                    System.err.println("Produit avec id : " + l + " introuvable");
                }

            }
            return out;
        }else {
            ArrayList<Produit> cl = new ArrayList<Produit>();
            return cl;
        }
    }

    public String searchFournisseurNumber(float idfournisseur) {
        Transaction tx = Main.getAppM().searchFournisseurNumber(idfournisseur);
        if (tx.getLevel() != Alert.AlertType.NONE && tx.getLevel()!= Alert.AlertType.ERROR) {
            ArrayList<String> name = (ArrayList<String>) tx.getCreatedObj();
            if(name.size() != 0){
                return name.get(0);
            }
        }
        return "";
    }

    public long searchQtyOfProduit(float id){
        Transaction tx = Main.getAppM().searchQtyOfProduit(id);
        if(tx.getLevel() != Alert.AlertType.NONE && tx.getLevel()!= Alert.AlertType.ERROR){
            ArrayList<Long> results = new ArrayList<Long>();
            results = (ArrayList<Long>) tx.getCreatedObj();
            return results.get(0);
        }else{
            return -1;
        }
    }

    public void createCommande(ArrayList<Long> listearticle, float reduction, String typepaiement, Adresse adresselivr, Date datelivraison, long ID_client) {
        Transaction tx = Main.getAppM().createCommande(listearticle, reduction, typepaiement, adresselivr, datelivraison,ID_client);
        if (tx.getLevel() != Alert.AlertType.NONE) {
            showDialog(tx,"Creation commande");
            if (tx.getLevel() != Alert.AlertType.ERROR) {
                notifyNewCommande((Commande) tx.getCreatedObj());
                for(long l : listearticle){
                    Transaction tmp = Main.getAppM().removeOneOfProduct(l);
                    if(tmp.getLevel() == Alert.AlertType.NONE && tmp.getLevel()== Alert.AlertType.ERROR){
                        System.err.println("The following error happen during the stock operation of " + l +  " : " +  tmp.getMessage());
                    }
                }
            }
        }
    }

    public void notifySelectedClient(Client c) {
        Main.getAppEventDisp().notifySelectedClient(c);
    }
}
