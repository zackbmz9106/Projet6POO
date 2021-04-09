package logic;

import commons.Adresse;
import database.DatabaseInterface;
import database.Transaction;
import magasin.Client;

import java.util.Date;

public class AppModel {
    private DatabaseInterface dBi;

    public void initDB() {
        dBi = new DatabaseInterface();
        dBi.lauchDatabase();
    }

    public Transaction createClient(String nom, String prenom, Adresse adresse, Date dateDeNaissance, String mail, String numerotel, boolean carteFidelite) {
        Client c = new Client(nom, prenom, adresse, dateDeNaissance, mail, numerotel, carteFidelite);
        Transaction tx = new Transaction(dBi);
        c.create(tx);
        tx.setCreatedObj(c);
        return tx;
    }
}
