package magasin;

import database.Transaction;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBObject {
    protected long ID;
    protected String tableName;

    public DBObject(String tableName) {
        this.tableName = tableName;
    }


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

    public String arrayToDB(ArrayList<Long> l ){
        StringBuilder out = new StringBuilder();
        for(Long e : l){
            out.append(String.valueOf(e)).append("^");
        }
        return out.toString();
    }


    public ArrayList<Long> DBtoArray(String in){
        ArrayList<Long> out = new ArrayList<Long>();
        String[] split = in.split("\\^");
        for(String s : split){
            out.add(Long.parseLong(s));
        }
        return out;
    }
}
