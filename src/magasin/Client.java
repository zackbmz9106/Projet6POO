package magasin;

import commons.Adresse;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class Client {
	private  String nom;
	private  String prenom;
	private Adresse adresse;
	private Date dateDeNaissance;
	private String mail;
	private int numerotel;
	private boolean carteFidelite;
	private int pointFidelite; 
	private int ID;

	public Client(String nom,String prenom,Adresse adresse,Date dateDeNaissance,String mail,int numerotel,boolean cartefidelite) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.dateDeNaissance = dateDeNaissance;
		this.mail = mail;
		this.numerotel = numerotel;
		this.carteFidelite = cartefidelite;
		this.pointFidelite = 0 ;

	}
	public void injectToSql(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
			stmt = conn.prepareStatement(
					"insert into Client(nom, prenom, adresse,dateDeNaissance,mail,numerotel,carteFidelite,pointFidelite) values(?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1,getNom());
		stmt.setString(2,getPrenom());
		stmt.setString(3,getAdresseString());
		stmt.setDate(4, (java.sql.Date) getDateDeNaissance());
		stmt.setString(5,getMail());
		stmt.setInt(6,getNumerotel());
		stmt.setBoolean(7,isCarteFidelite());
		stmt.setInt(8,getPointFidelite());
		stmt.executeUpdate();

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			this.ID = rs.getInt(1);
	}
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public String getAdresseString(){
		return this.adresse.toString();
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
	public Date getDateDeNaissance(){
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
		return "Client : " + this.prenom + " " +this.nom + " habitant à " + this.adresse.toString() +" née le " + this.dateDeNaissance
				+  "mail : " + this.mail + "n° tel " + this.numerotel + " avec " + this.pointFidelite + " point de fidelite" ; }
}
