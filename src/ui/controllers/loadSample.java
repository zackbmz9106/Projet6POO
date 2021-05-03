package ui.controllers;

import commons.Adresse;
import database.DatabaseInterface;
import database.Transaction;
import magasin.*;
import ui.Main;

import java.util.ArrayList;
import java.util.Date;

public class loadSample {

    public loadSample(DatabaseInterface dBi) {
        Transaction tx = new Transaction(dBi);

        Fournisseur Tishiba = new Fournisseur("Tishiba");
        Tishiba.create(tx);

        Adresse a = new Adresse("Avenue des fleurs", "21", "32400", "Villeux");
        Date d = new Date(1981 - 1900, 10 -1, 11);
        Client c = new Client("SCHMITT", "Marc", a, d, "marc.schmitt14@gmail.com", "0607265678", false);
        c.create(tx);

        Produit Ordi1 = new Produit("Ordinateur Portable", "Tishaba", "L2EIR-SUPER", 1240, false, 0, Tishiba.getID());
        Ordi1.create(tx);
        Main.getStock().createStockOfId(tx, Ordi1.getId(), 89);
        Produit Ordi2 = new Produit("Ordinateur tour", "Tishiba", "R450-MEGA", 2500, true, (float) 0.90, Tishiba.getID());
        Ordi2.create(tx);
        Main.getStock().createStockOfId(tx, Ordi2.getId(), 32);

        ArrayList<Long> listecommande1 = new ArrayList<Long>();
        listecommande1.add(Ordi1.getID());
        listecommande1.add(Ordi2.getID());
        Commande com = new Commande(listecommande1, 0, "CB", a, new Date(2020 - 1900, 12 -1, 24), c.getID());
        com.create(tx);

        Employe e1 = new Employe("David Rochefour", 2974, "Vendeur");
        e1.create(tx);


        Main.getAppEventDisp().notifyForceReload();
    }
}
