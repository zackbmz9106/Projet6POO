package magasin;

import database.Transaction;
import javafx.scene.control.Alert;

import java.sql.*;

public class Fournisseur extends DBObject implements IdbInterface {
    String nomFournisseur;

    public Fournisseur(String nomFournisseur) {
        super("Fournisseur");
        this.nomFournisseur = nomFournisseur;
    }


    public Fournisseur() {
        super("Fournisseur");
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
                    case "nomFournisseur" -> stmt.setString(i, this.nomFournisseur);
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
                    this.nomFournisseur = rs.getString("listeArticle");
                }
                transaction.succesfullMessage();
            }
        } catch (SQLException e) {
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
                    "insert into " + this.tableName + "(nomFournisseur) values(?)",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, nomFournisseur);
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

}
