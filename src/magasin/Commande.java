/*
package magasin;

import commons.Adresse;
import database.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


public class Commande implements IdbInterface {

    private ArrayList<Long> listeArticle;
    private float reduction;
    private final MoyenDePaiment typePaiement;
    private final Adresse adresseLivr;
    private Date dateLivraison;
    private long ID;
    private long ID_client;


    public Commande(ArrayList<Long> listearticle, float reduction, MoyenDePaiment typepaiement, Adresse adresselivr, Date datelivraison, long ID_client) {
        this.listeArticle = listearticle;
        this.reduction = reduction;
        this.typePaiement = typepaiement;
        this.adresseLivr = adresselivr;
        this.dateLivraison = datelivraison;
        this.ID_client = ID_client;
    }

    @Override
    public boolean create(Transaction Cobjt) {
        //creer l'incsrpition depuis les valeurs de l'object
        Connection conn = Cobjt.getdBi().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "insert into Commande(listeArticle, reduction, typePaiement, adresseLivr, dateLivraison,ID_client) values(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setArray(1, (Array) this.listeArticle);
            stmt.setFloat(2, this.reduction);
            stmt.setString(3, this.typePaiement.toString());
            stmt.setString(4, this.adresseLivr.toDB());
            stmt.setDate(5, (java.sql.Date) this.dateLivraison);
            stmt.setLong(6, this.ID_client);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                this.ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            Cobjt.onerrorCallback(e.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public boolean update(Transaction objt, String[] nomsDeChampsAMettreAjour) {
        //remplacer les elements modifier dans le l'inscription sql
        try {
            Connection conn = objt.getdBi().getConnection();
            String sql = "UPDATE Commande SET";
            for (String champ : nomsDeChampsAMettreAjour) {
                sql += champ + "=?";
            }
            sql += "WHERE id=" + this.ID;
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 1; i >= nomsDeChampsAMettreAjour.length; i++) {
                switch (nomsDeChampsAMettreAjour[i]) {
                    case "listeArticle" -> stmt.setArray(i, (Array) this.listeArticle);
                    case "reduction" -> stmt.setFloat(i, this.reduction);
                    case "typePaeiment" -> stmt.setString(i, this.typePaiement.toString());
                    case "dateLivraison" -> stmt.setDate(i, (java.sql.Date) this.dateLivraison);
                    case "adresseLivr" -> stmt.setString(i, this.adresseLivr.toDB());
                    case "ID_Client" -> stmt.setLong(i, this.ID_client);

                }
            }
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            objt.onerrorCallback(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean load(Transaction objt, int id) {
        //cherche l'inscription avec son id et copie les valeurs dans l'obj
        try {
            Connection conn = objt.getdBi().getConnection();
            ResultSet rs = conn.prepareStatement("SELECT * FROM Client WHERE id =" + id).executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    this.listeArticle = (ArrayList<Long>) rs.getArray("listeArticle");
                    this.reduction = rs.getFloat("reduction");
                    this.typePaiement.fromString(rs.getString("typePaiement"));
                    this.adresseLivr.fromDB(rs.getString("adresseLivr"));
                    this.dateLivraison = rs.getDate("dateLivraison");
                    this.ID_client = rs.getLong("ID_Client");
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            objt.onerrorCallback(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Long> query(Transaction objt) {
        //TODO : finir l'implementation
        ArrayList<Long> out = new ArrayList<Long>();
        return out;
    }

    @Override
    public boolean delete(Transaction objt, long id) {
        //Delete l'inscription de l'id donnée
        Connection conn = objt.getdBi().getConnection();
        try {
            ResultSet rs = conn.prepareStatement("DELETE FROM Commande WHERE id =" + ID).executeQuery();
        } catch (SQLException e) {
            objt.onerrorCallback(e.getMessage());
            return false;
        }
        return true;
    }

}
*/
