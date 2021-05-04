package logic;

import commons.Adresse;
import database.DatabaseInterface;
import database.QueryDB;
import database.Transaction;
import magasin.*;
import ui.Main;

import java.util.ArrayList;
import java.util.Date;

public class AppModel {

    private DatabaseInterface dBi;

    @Deprecated
    public DatabaseInterface getdBi() {
        return dBi;
    }

    public void initDB() {
        dBi = new DatabaseInterface();
        dBi.lauchDatabase();
    }

    public Transaction createClient(String prenom, String nom, Adresse adresse, Date dateDeNaissance, String mail, String numerotel, boolean carteFidelite) {
        Client c = new Client(nom, prenom, adresse, dateDeNaissance, mail, numerotel, carteFidelite);
        Transaction tx = new Transaction(dBi);
        c.create(tx);
        tx.setCreatedObj(c);
        return tx;
    }

    public Transaction createProduit(String typeArticle, String marque, String nomArticle, float prixArticle, boolean isSolde, float solde, long ID_fournisseur) {
        Produit p = new Produit(typeArticle, marque, nomArticle, prixArticle, isSolde, solde, ID_fournisseur);
        Transaction tx = new Transaction(dBi);
        p.create(tx);
        tx.setCreatedObj(p);
        return tx;
    }

    public Transaction searchFournisseur(String nomFournisseur) {
        Transaction tx = new Transaction(dBi);
        QueryDB qDB = new QueryDB("nomFournisseur", nomFournisseur, "", "id");
        Fournisseur f = new Fournisseur();
        f.query(tx, qDB);
        return tx;
    }

    public Transaction searchEmployeExist(int numEmployee) {
        Transaction tx = new Transaction(dBi);
        QueryDB qDB = new QueryDB("numEmploye", numEmployee, "", "id");
        Employe e = new Employe();
        e.query(tx, qDB);
        return tx;

    }

    public Transaction createEmploye(String nomEmploye, int numEmploye, String typePoste) {
        Employe e = new Employe(nomEmploye, numEmploye, typePoste);
        Transaction tx = new Transaction(dBi);
        e.create(tx);
        tx.setCreatedObj(e);
        return tx;
    }

    /*
     * Return Tx contenant un Arraylist de Long contenant les ids des différents objs trouvés
     * */
    public Transaction searchAll(String NomTable) {
        Transaction tx = new Transaction(dBi);
        QueryDB qDB = new QueryDB("", "", "", "id");
        DBObject obj = new DBObject(NomTable);
        obj.query(tx, qDB);
        return tx;
    }

    public Transaction deleteDBObject(DBObject o) {
        Transaction tx = new Transaction(dBi);
        o.delete(tx);
        return tx;
    }

    public Transaction searchFournisseurNumber(float idFournisseur) {
        Transaction tx = new Transaction(dBi);
        DBObject dbo = new DBObject("Fournisseur");
        dbo.query(tx, new QueryDB("", "", "", "nomFournisseur"));
        return tx;
    }

    public Transaction searchQtyOfProduit(float id) {
        Transaction tx = new Transaction(dBi);
        QueryDB qdb = new QueryDB("id_produit", id, "", "quantite");
        Main.getStock().query(tx, qdb);
        return tx;
    }

    public Transaction createCommande(ArrayList<Long> listearticle, float reduction, String typepaiement, Adresse adresselivr, Date datelivraison, long id_client) {
        Commande c = new Commande(listearticle, reduction, typepaiement, adresselivr, datelivraison, id_client);
        Transaction tx = new Transaction(dBi);
        c.create(tx);
        tx.setCreatedObj(c);
        return tx;
    }

    public Transaction removeOneOfProduct(long l) {
        Transaction tx = new Transaction(dBi);
        Main.getStock().removeOne(tx, l);
        return tx;
    }

    public Transaction loadClient(long id_client) {
        Transaction tx = new Transaction(dBi);
        Client c = new Client();
        c.load(tx, id_client);
        tx.setCreatedObj(c);
        return tx;
    }

    public Transaction searchProduit(long l) {
        Transaction tx = new Transaction(dBi);
        Produit p = new Produit();
        p.load(tx, l);
        tx.setCreatedObj(p);
        return tx;
    }
}
