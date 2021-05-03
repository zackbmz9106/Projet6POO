package database;

public class QueryDB {
    String nomChampDB;
    String valeur;
    String sortType;
    String table;

    public QueryDB(String nomChampDB, Object valeur, String sortType, String table) {
        this.nomChampDB = nomChampDB;
        this.valeur = String.valueOf(valeur);
        this.sortType = sortType;
        this.table = table;
    }

    public String construcQuery(String tableName) {
        if (table.equals("")) {
            table = "*";
        }
        if (valeur.equals("") || nomChampDB.equals("")) {
            return "SELECT " + table + " FROM " + tableName;
        }
        if (sortType.equals("")) {
            return "SELECT " + table + " FROM " + tableName + " WHERE " + nomChampDB + "= \"" + valeur + "\"";
        } else {
            return "SELECT " + table + " FROM " + tableName + " WHERE " + nomChampDB + "= \"" + valeur + "\" SORT BY " + sortType;
        }
    }
}
