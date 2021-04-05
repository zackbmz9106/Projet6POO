/*
package magasin;

import database.Transaction;

import java.sql.*;
import java.util.ArrayList;

public class Produit implements IdbInterface {
    private String typeArticle;
    private String marque;
    private String nomArticle;
    private float prixArticle;
    private boolean isSolde;
    private float solde;
    private long ID;
    private long ID_fournisseur;

    public Produit(String typeArticle, String marque, String nomArticle, float prixArticle, boolean isSolde, float solde, long ID_fournisseur) {
        this.typeArticle = typeArticle;
        this.marque = marque;
        this.nomArticle = nomArticle;
        this.prixArticle = prixArticle;
        this.isSolde = isSolde;
        this.solde = solde;
        this.ID_fournisseur = ID_fournisseur;
    }

    @Override
    public boolean update(Transaction objt, String[] nomsDeChampsAMettreAjour) {
        //remplacer les elements modifier dans le l'inscription sql
        try {
            Connection conn = objt.getdBi().getConnection();
            String sql = "UPDATE Client SET";
            for (String champ : nomsDeChampsAMettreAjour) {
                sql += champ + "=?";
            }
            sql += "WHERE id=" + this.ID;
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 1; i >= nomsDeChampsAMettreAjour.length; i++) {
                switch (nomsDeChampsAMettreAjour[i]) {
                    case "typeArticle" -> stmt.setString(i, this.typeArticle);
                    case "marque" -> stmt.setString(i, this.marque);
                    case "nomArticle" -> stmt.setString(i, this.nomArticle);
                    case "prixArticle" -> stmt.setFloat(i, this.prixArticle);
                    case "isSolde" -> stmt.setBoolean(i, this.isSolde);
                    case "solde" -> stmt.setFloat(i, this.solde);
                    case "ID_fournisseur" -> stmt.setLong(i, this.ID_fournisseur);
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
                    this.typeArticle = rs.getString("typeArticle");
                    this.marque = rs.getString("marque");
                    this.nomArticle = rs.getString("nomArticle");
                    this.prixArticle = rs.getFloat("prixArticle");
                    this.isSolde = rs.getBoolean("isSolde");
                    this.solde = rs.getFloat("solde");
                    this.ID_fournisseur = rs.getLong("ID_fournisseur");
                    this.ID = rs.getLong("id");
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
    public boolean create(Transaction objt) {
        //creer l'incsrpition depuis les valeurs de l'object
        Connection conn = objt.getdBi().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "insert into Produit(typeArticle,marque,nomArticle,prixArticle,isSolde,solde,ID) values(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.typeArticle);
            stmt.setString(2, this.marque);
            stmt.setString(3, this.nomArticle);
            stmt.setFloat(4, this.prixArticle);
            stmt.setBoolean(5, this.isSolde);
            stmt.setFloat(6, this.solde);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                this.ID = rs.getInt(1);
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
            ResultSet rs = conn.prepareStatement("DELETE FROM Produit WHERE id =" + id).executeQuery();
        } catch (SQLException e) {
            objt.onerrorCallback(e.getMessage());
            return false;
        }
        return true;
    }


    public String getTypeArticle() {
        return typeArticle;
    }

    public String getMarque() {
        return marque;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public float getPrixArticle() {
        return prixArticle;
    }

    public boolean isSolde() {
        return isSolde;
    }

    public float getSolde() {
        return solde;
    }
}
*/
