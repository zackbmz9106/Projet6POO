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
        Fournisseur Apple = new Fournisseur("Apple");
        Apple.create(tx);
        Fournisseur Samsung = new Fournisseur("Samsung");
        Samsung.create(tx);
        Fournisseur Sony = new Fournisseur("Sony");
        Sony.create(tx);
        Fournisseur HP = new Fournisseur("HP");
        HP.create(tx);
        Fournisseur Condor = new Fournisseur("Condor");
        Condor.create(tx);
        Fournisseur LG = new Fournisseur("LG");
        LG.create(tx);

        Adresse a = new Adresse("Avenue des fleurs", "21", "32400", "Villeux");
        Adresse b = new Adresse("Quai de seine", "306", "75004", "Paris");
        Adresse f = new Adresse("Avenue Darblay", "27", "91100", "Corbeil-Essonnes");
        Adresse e = new Adresse("Rue de paris", "9", "91100", "Corbeil-Essonnes");
        Adresse g = new Adresse("Boulevard Jean Jaures", "23", "91000", "Evry");
        Adresse j = new Adresse("Rue des mozards", "66", "93120", "La Courneuve");

        Date d = new Date(1981 - 1900, 10 - 1, 11);
        Date d1 = new Date(2000 - 1900, 4 - 1, 9);
        Date d2 = new Date(1966 - 1900, 9 - 1, 31);
        Date d3 = new Date(1970 - 1900, 12 - 1, 13);
        Date d4 = new Date(2000 - 1900, 6 - 1, 22);
        Date d5 = new Date(1998 - 1900, 1 - 1, 18);

        Client c = new Client("SCHMITT", "Marc", a, d, "marc.schmitt14@gmail.com", "0607265678", false);
        Client c1 = new Client("Musk", "Ellon", b, d1, "ellon.musk06@gmail.com", "0755829788", false);
        Client c2 = new Client("Mesnieres", "Kevin", f, d2, "kevi.msn@gmail.com", "0607658988", false);
        Client c3 = new Client("Forni", "Xavier", e, d3, "xavier.forni@gmail.com", "0788996545", false);
        Client c4 = new Client("Othman", "Tamra", g, d4, "otta66@gmail.com", "0645366545", true);
        Client c5 = new Client("Malin", "André", e, d5, "malin.andre06@gmail.com", "0658987458", true);
        Client c6 = new Client("Creon", "Michel", a, new Date(), "creon@free.fr", "0658987245", false);

        c.create(tx);
        c1.create(tx);
        c2.create(tx);
        c3.create(tx);
        c4.create(tx);
        c5.create(tx);
        c6.create(tx);

        Produit Ordi1 = new Produit("Ordinateur Portable", "Tishaba", "L2EIR-SUPER", 1240, false, 0, Tishiba.getID());
        Ordi1.create(tx);
        Main.getStock().createStockOfId(tx, Ordi1.getId(), 89);

        Produit Ordi2 = new Produit("Ordinateur tour", "Tishiba", "R450-MEGA", 2500, true, (float) 0.90, Tishiba.getID());
        Ordi2.create(tx);
        Main.getStock().createStockOfId(tx, Ordi2.getId(), 32);

        Produit Ecouteurs1 = new Produit("Ecouteurs ", "Apple", "Airpods-Pro", 240, false, 0, Apple.getID());
        Ecouteurs1.create(tx);
        Main.getStock().createStockOfId(tx, Ecouteurs1.getId(), 64);


        Produit Enceinte1 = new Produit("Enceinte sonore", "Sony", "AZETT650", 480, true, (float) 0.40, Sony.getID());
        Enceinte1.create(tx);
        Main.getStock().createStockOfId(tx, Enceinte1.getId(), 10);

        Produit Tele1 = new Produit("Televiseur Smart", "LG", "LG UHD 65", 1260, false, (float) 0, LG.getID());
        Tele1.create(tx);
        Main.getStock().createStockOfId(tx, Tele1.getId(), 6);

        ArrayList<Long> listecommande1 = new ArrayList<Long>();
        listecommande1.add(Ordi1.getID());
        listecommande1.add(Ordi2.getID());
        listecommande1.add(Ecouteurs1.getID());
        listecommande1.add(Enceinte1.getID());
        listecommande1.add(Tele1.getID());

        ArrayList<Long> listecommande2 = new ArrayList<Long>();
        listecommande2.add(Ecouteurs1.getID());
        listecommande2.add(Ordi2.getID());
        listecommande2.add(Ordi1.getID());


        Commande com1 = new Commande(listecommande1, 0, "CB", a, new Date(2020 - 1900, 12, 24), c.getID());
        com1.create(tx);
        Commande com2 = new Commande(listecommande2, 20, "Especes", b, new Date(2020 - 1900, 05, 03), c1.getID());
        com2.create(tx);
        Commande com3 = new Commande(listecommande1, 30, "Cheque", f, new Date(), c2.getID());
        com3.create(tx);


        Employe e1 = new Employe("David Rochefour", 2974, "Vendeur");
        e1.create(tx);
        Employe e2 = new Employe("Yanis Kasmi", 1456, "Responsable Stock");
        e2.create(tx);
        Employe e3 = new Employe("Pascal Mapin", 156, "Caissier");
        e3.create(tx);
        Employe e4 = new Employe("Youssef Moussouni", 94, "Caissier");
        e4.create(tx);
        Employe e5 = new Employe("Charaf Parrai", 465, "Responsable Stock");
        e5.create(tx);
        Employe e6 = new Employe("Laurent Xanou", 206, "Livreur");
        e6.create(tx);


        Main.getAppEventDisp().notifyForceReload();
    }
}
