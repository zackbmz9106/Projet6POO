package database;

import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInterface {
    Connection conn;

    public void lauchDatabase() {
        DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
        configBuilder.setPort(3306); // OR, default: setPort(0); => autom. detect free port
        configBuilder.setDataDir("/home/theapp/db"); // just an example
        DB db = null;
        Connection conn = null;
        try {
            db = DB.newEmbeddedDB(configBuilder.build());
            db.start();
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        QueryRunner qr = new QueryRunner();
        try {
            qr.update(this.conn, "CREATE TABLE Client(nom VARCHAR(100) , prenom VARCHAR(100) , adress VARCHAR(100) ,dateDeNaissance DATE ,mail VARCHAR(100), numerotel INT(20),carteFidelite BOOL,pointFidelite INT(100))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}

