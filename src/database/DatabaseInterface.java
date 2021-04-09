package database;

import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DatabaseInterface {
    Connection conn;

    public void lauchDatabase() {
        DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
        configBuilder.setPort(3306); // OR, default: setPort(0); => autom. detect free port
        configBuilder.setDataDir("/tmp/app"); // just an example
        DB db = null;
        String dbName = "test"; // never test
        try {
            db = DB.newEmbeddedDB(configBuilder.build());
            db.start();
//            db.createDB(dbName);
            this.conn = DriverManager.getConnection(configBuilder.getURL(dbName), "root", "");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE Client(nom VARCHAR(100) , prenom VARCHAR(100) , adresse VARCHAR(100) ,dateDeNaissance DATE ,mail VARCHAR(100), numerotel VARCHAR(20),carteFidelite BOOL,pointFidelite INT(100))";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }
}

