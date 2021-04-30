package magasin;

import commons.Adresse;
import database.QueryDB;
import database.Transaction;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class Commande extends DBObject implements IdbInterface {

    private ArrayList<Long> listeArticle;
    private float reduction;
    private String typePaiement;
    private final Adresse adresseLivr;
    private Date dateLivraison;
    private long ID;
    private long ID_client;


    public Commande(ArrayList<Long> listearticle, float reduction, String typepaiement, Adresse adresselivr, Date datelivraison, long ID_client) {
        super("Commande");
        this.listeArticle = listearticle;
        this.reduction = reduction;
        this.typePaiement = typepaiement;
        this.adresseLivr = adresselivr;
        this.dateLivraison = datelivraison;
        this.ID_client = ID_client;
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
            stmt.setDate(5, (java.sql.Date) this.dateLivraison);
            stmt.setLong(6, this.ID_client);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                this.ID = rs.getInt(1);
            }
            tx.succesfullMessage();
        } catch (SQLException e) {
            tx.setMessage(e.getMessage());
            tx.setLevel(Alert.AlertType.ERROR);
        }
    }


    @Override
    public void update(Transaction tx, String[] nomsDeChampsAMettreAjour) {
        //remplacer les elements modifier dans le l'inscription sql
        try {
            Connection conn = tx.getdBi().getConnection();
            String sql = "UPDATE " + this.tableName + " SET";
            for (String champ : nomsDeChampsAMettreAjour) {
                sql += champ + "=?";
            }
            sql += "WHERE id=" + this.ID;
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 1; i >= nomsDeChampsAMettreAjour.length; i++) {
                switch (nomsDeChampsAMettreAjour[i]) {
                    case "listeArticle" -> stmt.setString(i, arrayToDB(this.listeArticle));
                    case "reduction" -> stmt.setFloat(i, this.reduction);
                    case "typePaeiment" -> stmt.setString(i, this.typePaiement);
                    case "dateLivraison" -> stmt.setDate(i, (java.sql.Date) this.dateLivraison);
                    case "adresseLivr" -> stmt.setString(i, this.adresseLivr.toDB());
                    case "ID_Client" -> stmt.setLong(i, this.ID_client);

                }
            }
            stmt.executeUpdate();
            tx.succesfullMessage();
        } catch (SQLException e) {
            tx.setMessage(e.getMessage());
            tx.setLevel(Alert.AlertType.ERROR);
        }

    }

    @Override
    public void load(Transaction transaction, long id) {
        //cherche l'inscription avec son id et copie les valeurs dans l'obj
        try {
            Connection conn = transaction.getdBi().getConnection();
            ResultSet rs = conn.prepareStatement("SELECT * FROM Client WHERE id =" + id).executeQuery();
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
            transaction.setMessage(e.getMessage());
            transaction.setLevel(Alert.AlertType.ERROR);
        }

    }

    @Override
    public void query(Transaction tx, QueryDB qDB) {
        //TODO : finir l'implementation
        ArrayList<Long> out = new ArrayList<Long>();
        Connection conn = tx.getdBi().getConnection();
        ResultSet rs;
        try {
            rs = conn.prepareStatement(qDB.construcQuery(this.tableName)).executeQuery();
            tx.succesfullMessage();
            while (rs.next()) {
                out.add(rs.getLong(1));
            }
        } catch (SQLException e) {
            tx.setMessage(e.getMessage());
            tx.setLevel(Alert.AlertType.ERROR);
        }
        tx.setCreatedObj(out);


    }

}
