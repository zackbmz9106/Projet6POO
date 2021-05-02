package magasin;

import commons.Adresse;
import database.Transaction;
import org.junit.jupiter.api.Test;
import ui.Main;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private boolean check2Client(Client c1, Client c2){
        return c1.getPrenom().equals(c2.getPrenom()) && c1.getNom().equals(c2.getNom()) && c1.getAdresse().equals(c2.getAdresse()) && c1.getDateDeNaissance().toInstant() == c2.getDateDeNaissance().toInstant() && c1.getMail().equals(c2.getMail())&& c1.getNumerotel().equals(c2.getNumerotel()) && c1.isCarteFidelite() == c2.isCarteFidelite();
    }

    private boolean clientCheckInscription(Client c,String prenom, String nom, Adresse adresse, Date dateDeNaissance, String mail, String numerotel, boolean carteFidelite){
        return c.getPrenom().equals(prenom) && c.getNom().equals(nom) && c.getAdresse().equals(adresse) && c.getDateDeNaissance().toInstant() == dateDeNaissance.toInstant() && c.getMail().equals(mail)&& c.getNumerotel().equals(numerotel) && c.isCarteFidelite() == carteFidelite;
    }

    @Test
    void testClient(){
        String prenom="Toto";
        String nom="Titi";
        Adresse a = new Adresse("Av du President","12","10100","Icilesmoulineaux");
        Date naissance = new Date(2000,2,4);
        String mail = "toto@mail.fr";
        String numerotel= "+33234432";
        Boolean carteFidel = false ;
        Client c = new Client(prenom,nom,a,naissance,mail,numerotel,carteFidel);
        assertTrue(clientCheckInscription(c,nom,prenom,a,naissance,mail,numerotel,carteFidel));
        Transaction tx = Main.getAppM().createClient(nom,prenom,a,naissance,mail,numerotel,carteFidel);
        assertTrue(check2Client(c, (Client) tx.getCreatedObj()));
        assertTrue(clientCheckInscription((Client) tx.getCreatedObj(),nom,prenom,a,naissance,mail,numerotel,carteFidel));
        assertTrue(((Client) tx.getCreatedObj()).getID() != 0);
        Transaction clientSearch = Main.getAppM().searchAll("Client");
        assertTrue(clientSearch.getCreatedObj() != null);
        assertEquals(((Client) clientSearch.getCreatedObj()).getID(),((Client) tx.getCreatedObj()).getID());
        Client loaded = new Client();
        Transaction clientLoader = new Transaction(Main.getAppM().getdBi());
        loaded.load(clientLoader,((Client) tx.getCreatedObj()).getID());
        assertTrue(check2Client(loaded, (Client) clientLoader.getCreatedObj()));
        assertTrue(check2Client(c,loaded));

    }
}