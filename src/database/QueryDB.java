package database;

public class QueryDB {
    String nomChampDB;
    String valeur;
    String sortType;

    public QueryDB(String nomChampDB, Object valeur, String sortType) {
        this.nomChampDB = nomChampDB;
        this.valeur = String.valueOf(valeur);
        this.sortType = sortType;
    }

    public String construcQuery(String tableName) {
        if (sortType.equals("")) {
            return "SELECT id FROM " + tableName + " WHERE " + nomChampDB + "= \"" + valeur + "\"";
        } else {
            return "SELECT id FROM " + tableName + " WHERE " + nomChampDB + "= \"" + valeur + "\" SORT BY " + sortType;
        }
    }
}
