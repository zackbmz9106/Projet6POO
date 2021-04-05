package magasin;

import database.Transaction;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBObject {
    protected long ID;
    protected String tableName;

    public DBObject(String tableName){
        this.tableName=tableName;
    }


    public void delete(Transaction transaction) {
        Connection conn = transaction.getdBi().getConnection();
        try {
            ResultSet rs = conn.prepareStatement("DELETE FROM "+this.tableName+" WHERE id =" + this.ID).executeQuery();
            transaction.succesfullMessage();
        } catch (SQLException e) {
            transaction.setMessage(e.getMessage());
            transaction.setLevel(Alert.AlertType.ERROR);
        }

    }


}
