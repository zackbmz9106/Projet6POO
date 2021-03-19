package database;

public class CObjTransaction {
    private final DatabaseInterface DBi;

    public CObjTransaction(DatabaseInterface dbi) {
        this.DBi = dbi;
    }

    public DatabaseInterface getDDitf() {
        return this.DBi;
    }

    public void onerrorCallback(String ErrorMessage) {
        System.err.println(ErrorMessage);
    }

}
