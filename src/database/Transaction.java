package database;

import javafx.scene.control.Alert;

public class Transaction {
    private final DatabaseInterface DBi;
    private Alert.AlertType level ;
    private String message ;

    public void succesfullMessage(){
        this.message = "Réussite !";
        this.level = Alert.AlertType.INFORMATION;
    }


    public Transaction(DatabaseInterface dbi) {
        this.DBi = dbi;
        this.message = "";
        this.level = Alert.AlertType.NONE;
    }
    public String getMessage() {
        return message;
    }
    public DatabaseInterface getdBi() {
        return this.DBi;
    }

    public void setLevel(Alert.AlertType level) {
        this.level = level;
    }

    public Alert.AlertType getLevel() {
        return level;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
