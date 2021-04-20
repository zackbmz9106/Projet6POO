package magasin;

import database.Transaction;
import javafx.scene.control.Alert;

import java.sql.*;

public class Employe extends DBObject implements IdbInterface{
    String nomEmploye;
    int numEmploye;
    String typePoste;
    long id;

    public Employe(String nomEmploye,int numEmploye,String typePoste){
        super("Employe");
        this.nomEmploye = nomEmploye;
        this.numEmploye = numEmploye;
        this.typePoste = typePoste;
    }

    public Employe() {
//        A utiliser pour rechercher dans la base
        super("Employe");
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
                    case "nomEmploye" -> stmt.setString(i, this.nomEmploye);
                    case "numEmploye" -> stmt.setInt(i, this.numEmploye);
                    case "typePoste" -> stmt.setString(i, this.typePoste);
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
            ResultSet rs = conn.prepareStatement("SELECT * FROM " + this.tableName + " WHERE id =" + id).executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    this.nomEmploye = rs.getString("nomEmploye");
                    this.numEmploye = rs.getInt("numEmploye");
                    this.typePoste= rs.getString("dateDeNaissance");
                    this.ID = rs.getLong("id");
                }
            }
            transaction.succesfullMessage();
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
            String sql = "insert into " + this.tableName + "(nomEmploye,numEmploye,typePoste) values(?,?,?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.nomEmploye);
            stmt.setInt(2, this.numEmploye);
            stmt.setString(3, this.typePoste);
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
    public String getObjectDescriptor() {
        return this.nomEmploye;
    }
}
