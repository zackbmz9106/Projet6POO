package database;

public class CObjTransaction {
    private final DatabaseInterface DBi;

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    private String lastErrorMessage;

    public CObjTransaction(DatabaseInterface dbi) {
        this.DBi = dbi;
    }

    public DatabaseInterface getdBi() {
        return this.DBi;
    }

    public void onerrorCallback(String ErrorMessage) {
        lastErrorMessage = ErrorMessage;
        System.err.println(ErrorMessage);
    }

}
