package logic;

import commons.Adresse;
import ui.Main;

import java.util.Date;

public class AppController  {


    public void createClient(String nom, String prenom, Adresse adresse, Date dateDeNaissance, String mail, int numerotel, boolean carteFidelite){
        Main.getAppM().createClient(nom,prenom,adresse,dateDeNaissance,mail,numerotel,carteFidelite);
    }
}
