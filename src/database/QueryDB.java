package database;

public class QueryDB {
    String nomChampDB;
    String valeur;
    String sortType;

    public QueryDB(String nomChampDB,String valeur,String sortType){
        this.nomChampDB = nomChampDB;
        this.valeur = valeur;
        if(sortType.equals("")) {
            this.sortType = "ASC";
        }
    }
    public String construcQuery(String tableName){
        return "SELECT * FROM " + tableName + " WHERE " + nomChampDB +"="+ valeur + "ORDER BY " + sortType;
    }
}
