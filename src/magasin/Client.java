package magasin;

import commons.Adresse;
import database.CObjTransaction;
import database.IdbInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Client  implements IdbInterface  {
    private  String nom;
    private  String prenom;
    private Adresse adresse;
    private  Date dateDeNaissance;
    private String mail;
    private int numerotel;
    private boolean carteFidelite;
    private int pointFidelite;
    private long ID;

    public Client(String nom, String prenom, Adresse adresse, Date dateDeNaissance, String mail, int numerotel, boolean cartefidelite) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateDeNaissance = dateDeNaissance;
        this.mail = mail;
        this.numerotel = numerotel;
        this.carteFidelite = cartefidelite;
        this.pointFidelite = 0;

    }

    @Override
    public boolean create(CObjTransaction Cobjt) {
        //creer l'incsrpition depuis les valeurs de l'object
        Connection conn = Cobjt.getDDitf().getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "insert into Client(nom, prenom, adresse,dateDeNaissance,mail,numerotel,carteFidelite,pointFidelite) values(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, getNom());
            stmt.setString(2, getPrenom());
            stmt.setString(3, getAdresseString());
            stmt.setDate(4, (java.sql.Date) getDateDeNaissance());
            stmt.setString(5, getMail());
            stmt.setInt(6, getNumerotel());
            stmt.setBoolean(7, isCarteFidelite());
            stmt.setInt(8, getPointFidelite());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                this.ID = rs.getInt(1);
            }
        } catch (SQLException e) {
            Cobjt.onerrorCallback(e.getMessage());
            return false;
        }
        return true;
    }



    @Override
    public boolean update(CObjTransaction objt, String[] nomsDeChampsAMettreAjour) {
        //remplacer les elements modifier dans le l'inscription sql
        try {
            Connection conn = objt.getDDitf().getConnection();
            String sql = "UPDATE Client SET";
            for (String champ : nomsDeChampsAMettreAjour) {
                sql += champ + "=?";
            }
            sql += "WHERE id=" + this.ID;
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 1; i >= nomsDeChampsAMettreAjour.length; i++) {
                switch (nomsDeChampsAMettreAjour[i]) {
                    case "nom" -> stmt.setString(i, this.nom);
                    case "prenom" -> stmt.setString(i,this.prenom);
                    case "adresse" -> stmt.setString(i,this.adresse.toString());
                    case "dateDeNaissance" -> stmt.setDate(i, (java.sql.Date) this.dateDeNaissance);
                    case "mail" -> stmt.setString(i,this.mail);
                    case  "numerotel" -> stmt.setInt(i,this.numerotel);
                    case "carteFidelite" -> stmt.setBoolean(i,this.carteFidelite);
                    case "pointFidelite" -> stmt.setInt(i,this.pointFidelite);
                }
            }
            return stmt.executeUpdate() > 0;
        }catch (SQLException e) {
            Cobjt.onerrorCallback(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean load(CObjTransaction objt, int id) {
        //cherche l'inscription avec son id et copie les valeurs dans l'obj
        try {
            Connection conn = objt.getDDitf().getConnection();
            ResultSet rs = conn.prepareStatement("SELECT * FROM Client WHERE id =" + id).executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    this.nom = rs.getString("nom");
                    this.prenom = rs.getString("prenom");
                    this.adresse.fromString(rs.getString("adresse"));
                    this.dateDeNaissance = rs.getDate("dateDeNaissance");
                    this.mail = rs.getString("mail");
                    this.numerotel = rs.getInt("numerotel");
                    this.carteFidelite = rs.getBoolean("carteFidelite");
                    this.pointFidelite = rs.getInt("pointFidelite");
                    this.ID = rs.getLong("id");
                }
            }else{
                return false;
                }
        }catch (SQLException e){
            objt.onerrorCallback(e.getMessage());
            return false;
        }
        return true;
    }


    public ArrayList<Long> query(CObjTransaction objt) {
        //TODO : finir l'implementation
        ArrayList<Long> out = new ArrayList<Long>();
        return out;
    }

    @Override
    public boolean delete(CObjTransaction objt, int id) {
        //Delete l'inscription de l'id donnée
        Connection conn = objt.getDDitf().getConnection();
        try {
            ResultSet rs = conn.prepareStatement("DELETE FROM Client WHERE id =" + id).executeQuery();
        }catch (SQLException e) {
            objt.onerrorCallback(e.getMessage());
            return false;
        }
        return true;
    }


    //GETER/SETTER+TOSTRING

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getAdresseString() {
        return this.adresse.toString();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNumerotel() {
        return numerotel;
    }

    public void setNumerotel(int numerotel) {
        this.numerotel = numerotel;
    }

    public boolean isCarteFidelite() {
        return carteFidelite;
    }

    public void setCarteFidelite(boolean carteFidelite) {
        this.carteFidelite = carteFidelite;
    }

    public int getPointFidelite() {
        return pointFidelite;
    }

    public void setPointFidelite(int pointFidelite) {
        this.pointFidelite = pointFidelite;
    }

    public Date getDateDeNaissance() {
        return this.dateDeNaissance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return "Client : " + this.prenom + " " + this.nom + " habitant à " + this.adresse.toString() + " née le " + this.dateDeNaissance
                + "mail : " + this.mail + "n° tel " + this.numerotel + " avec " + this.pointFidelite + " point de fidelite";
    }
}
