package magasin;

import database.QueryDB;
import database.Transaction;
import javafx.scene.control.Alert;
import org.springframework.lang.Nullable;
import ui.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Stock extends DBObject implements IdbInterface {

    private long id_produit;
    private long qty;

    public Stock() {
        super("Stock");
    }

    public void setProduit(Produit p, long qty) {
        id_produit = p.getId();
        this.qty = qty;
    }

    @Override
    public void delete(Transaction transaction) {
        Connection conn = transaction.getdBi().getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM " + this.tableName + " WHERE id_produit =" + this.id_produit);
            preparedStatement.execute();
            transaction.succesfullMessage();
            transaction.setCreatedObj(this);
        } catch (SQLException e) {
            transaction.setEx(e);
            transaction.setMessage(e.getMessage());
            transaction.setLevel(Alert.AlertType.ERROR);
        }
    }

    @Override
    public void update(Transaction tx, @Nullable String[] nomsDeChampsAMettreAjour) {
        //remplacer les elements modifier dans le l'inscription sql
        try {
            Connection conn = tx.getdBi().getConnection();
            String sql = "UPDATE " + this.tableName + " SET";
            for (String champ : nomsDeChampsAMettreAjour) {
                sql += champ + "=?";
            }
            sql += "WHERE id=" + this.id_produit;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, this.qty);
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


    }

    @Override
    public void create(Transaction tx) {
        //creer l'incsrpition depuis les valeurs de l'object
        Connection conn = tx.getdBi().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "insert into " + this.tableName + "(id_produit,quantite) values(?,?)");
            stmt.setLong(1, this.id_produit);
            stmt.setLong(2, this.qty);
            stmt.executeUpdate();
            tx.succesfullMessage();
        } catch (SQLException e) {
            tx.setEx(e);
            tx.setMessage(e.getMessage());
            tx.setLevel(Alert.AlertType.ERROR);
        }

    }

    public void removeOne(Transaction tx, long l) {
        QueryDB qdb = new QueryDB("quantite", l, "", "id_produit");
        Main.getStock().query(tx, qdb);
        if (tx.getLevel() != Alert.AlertType.NONE && tx.getLevel() != Alert.AlertType.ERROR) {
            ArrayList<Long> results = (ArrayList<Long>) tx.getCreatedObj();
            this.id_produit = l;
            this.qty = results.get(0) - 1;
            Main.getStock().update(tx, null);
        } else {
            return;
        }
    }

    @Deprecated
    public void createStockOfId(Transaction tx, long id, long stock) {
        this.id_produit = id;
        this.qty = stock;
        this.create(tx);
    }
}
