package magasin;

import database.QueryDB;
import database.Transaction;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DBObject implements IdbInterface{
    protected long ID;
    protected String tableName;

    public DBObject(String tableName) {
        this.tableName = tableName;
    }

    public DBObject(Object ... args){}

    public void delete(Transaction transaction) {
        Connection conn = transaction.getdBi().getConnection();
        try {
            ResultSet rs = conn.prepareStatement("DELETE FROM " + this.tableName + " WHERE id =" + this.ID).executeQuery();
            transaction.succesfullMessage();
        } catch (SQLException e) {
            transaction.setMessage(e.getMessage());
            transaction.setLevel(Alert.AlertType.ERROR);
        }

    }
    public void query(Transaction tx, QueryDB qDB) {
        //TODO : finir l'implementation
        ArrayList<Long> out = new ArrayList<Long>();
        Connection conn = tx.getdBi().getConnection();
        ResultSet rs;
        try {
            String sql = qDB.construcQuery(this.tableName);
            rs = conn.prepareStatement(sql).executeQuery();
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

    public String arrayToDB(ArrayList<Long> l) {
        StringBuilder out = new StringBuilder();
        for (Long e : l) {
            out.append(e).append("^");
        }
        return out.toString();
    }


    public ArrayList<Long> DBtoArray(String in) {
        ArrayList<Long> out = new ArrayList<Long>();
        String[] split = in.split("\\^");
        for (String s : split) {
            out.add(Long.parseLong(s));
        }
        return out;
    }
}
