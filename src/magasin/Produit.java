package magasin;

import database.Transaction;
import javafx.scene.control.Alert;
import ui.Main;

import java.sql.*;

public class Produit extends DBObject implements IdbInterface {
    private String typeArticle;
    private String marque;
    private String nomArticle;
    private float prixArticle;
    private boolean isSolde;
    private float solde;
    private long ID_fournisseur;

    public Produit(String typeArticle, String marque, String nomArticle, float prixArticle, boolean isSolde, float solde, long ID_fournisseur) {
        super("Produit");
        this.typeArticle = typeArticle;
        this.marque = marque;
        this.nomArticle = nomArticle;
        this.prixArticle = prixArticle;
        this.isSolde = isSolde;
        this.solde = solde;
        this.ID_fournisseur = ID_fournisseur;
    }

    //    Search Only
    public Produit() {
        super("Produit");
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
                    case "typeArticle" -> stmt.setString(i, this.typeArticle);
                    case "marque" -> stmt.setString(i, this.marque);
                    case "nomArticle" -> stmt.setString(i, this.nomArticle);
                    case "prixArticle" -> stmt.setFloat(i, this.prixArticle);
                    case "isSolde" -> stmt.setBoolean(i, this.isSolde);
                    case "solde" -> stmt.setFloat(i, this.solde);
                    case "ID_fournisseur" -> stmt.setLong(i, this.ID_fournisseur);
                }
            }
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
                    this.typeArticle = rs.getString("typeArticle");
                    this.marque = rs.getString("marque");
                    this.nomArticle = rs.getString("nomArticle");
                    this.prixArticle = rs.getFloat("prixArticle");
                    this.isSolde = rs.getBoolean("isSolde");
                    this.solde = rs.getFloat("solde");
                    this.ID_fournisseur = rs.getLong("ID_fournisseur");
                    this.ID = rs.getLong("id");
                }
                transaction.succesfullMessage();
            }
        } catch (SQLException e) {
            transaction.setEx(e);
            transaction.setMessage(e.getMessage());
            transaction.setLevel(Alert.AlertType.ERROR);
        }
    }

    @Override
    public void create(Transaction tx) {
        //creer l'incsrpition depuis les valeurs de l'object
        Connection conn = tx.getdBi().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "insert into " + this.tableName + "(typeArticle,marque,nomArticle,prixArticle,isSolde,solde,ID_fournisseur) values(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.typeArticle);
            stmt.setString(2, this.marque);
            stmt.setString(3, this.nomArticle);
            stmt.setFloat(4, this.prixArticle);
            stmt.setBoolean(5, this.isSolde);
            stmt.setFloat(6, this.solde);
            stmt.setLong(7, this.ID_fournisseur);
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

    public String getFourName() {
        return Main.getAppC().searchFournisseurNumber(this.ID_fournisseur);
    }

    public float getPrixReel() {
        if (isSolde) {
            return prixArticle * solde;
        } else {
            return prixArticle;
        }
    }

    public long getId() {
        return this.ID;
    }
}
