package magasin;

import commons.Adresse;
import database.Transaction;
import javafx.scene.control.Alert;
import ui.Main;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Commande extends DBObject implements IdbInterface {

    private Adresse adresseLivr = new Adresse("", "", "", "");
    private ArrayList<Long> listeArticle;
    private float reduction;
    private String typePaiement;
    private Date dateLivraison;
    private long ID;
    private long ID_client;

    public Commande() {
        super("Commande");
    }
    public Commande(ArrayList<Long> listearticle, float reduction, String typepaiement, Adresse adresselivr, Date datelivraison, long ID_client) {
        super("Commande");
        this.listeArticle = listearticle;
        this.reduction = reduction;
        this.typePaiement = typepaiement;
        this.adresseLivr = adresselivr;
        this.dateLivraison = datelivraison;
        this.ID_client = ID_client;
    }

    public Adresse getAdresseLivr() {
        return adresseLivr;
    }

    public ArrayList<Long> getListeArticle() {
        return listeArticle;
    }

    public float getReduction() {
        return reduction;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public long getID_client() {
        return ID_client;
    }

    @Override
    public void create(Transaction tx) {
        //creer l'incsrpition depuis les valeurs de l'object
        Connection conn = tx.getdBi().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "insert into " + this.tableName + "(listeArticle, reduction, typePaiement, adresseLivr, dateLivraison,ID_client) values(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, arrayToDB(this.listeArticle));
            stmt.setFloat(2, this.reduction);
            stmt.setString(3, this.typePaiement);
            stmt.setString(4, this.adresseLivr.toDB());
            stmt.setDate(5, new java.sql.Date(this.dateLivraison.getTime()));
            stmt.setLong(6, this.ID_client);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                this.ID = rs.getInt(1);
            }
            tx.succesfullMessage();
        } catch (SQLException e) {
            tx.setEx(e);
            tx.setMessage(e.getMessage());
            tx.setLevel(Alert.AlertType.ERROR);
        }
    }


    @Override
    public void update(Transaction tx) {
        //remplacer les elements modifier dans le l'inscription sql
        Connection conn = tx.getdBi().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "insert into " + this.tableName + "(listeArticle, reduction, typePaiement, adresseLivr, dateLivraison,ID_client) values(?,?,?,?,?,?)");

            stmt.setString(1, arrayToDB(this.listeArticle));
            stmt.setFloat(2, this.reduction);
            stmt.setString(3, this.typePaiement);
            stmt.setString(4, this.adresseLivr.toDB());
            stmt.setDate(5, new java.sql.Date(this.dateLivraison.getTime()));
            stmt.setLong(6, this.ID_client);
            stmt.executeUpdate();
            tx.succesfullMessage();
        } catch (SQLException e) {
            tx.setEx(e);
            tx.setMessage(e.getMessage());
            tx.setLevel(Alert.AlertType.ERROR);
        }

    }

    @Override
    public void load(Transaction transaction, long id) {
        //cherche l'inscription avec son id et copie les valeurs dans l'obj
        try {
            Connection conn = transaction.getdBi().getConnection();
            ResultSet rs = conn.prepareStatement("SELECT * FROM " + this.tableName + " WHERE id =" + id).executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    this.listeArticle = DBtoArray(rs.getString("listeArticle"));
                    this.reduction = rs.getFloat("reduction");
                    this.typePaiement = rs.getString("typePaiement");
                    this.adresseLivr.fromDB(rs.getString("adresseLivr"));
                    this.dateLivraison = rs.getDate("dateLivraison");
                    this.ID_client = rs.getLong("ID_Client");
                }
                transaction.succesfullMessage();
            }
        } catch (SQLException e) {
            transaction.setEx(e);
            transaction.setMessage(e.getMessage());
            transaction.setLevel(Alert.AlertType.ERROR);
        }

    }

    public String getDesc() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return Main.getAppC().searchClient(this.getID_client()).getNom() + " " + date;
    }

    public float getEffectiveTotalPrice(){
        float out = 0;
        ArrayList<Produit> produitListFromCommande = Main.getAppC().getProduitListFromCommande(this);
        for(Produit p : produitListFromCommande){
            out += p.getPrixReel() * reduction;
        }
        return out;
    }
}
