package magasin;

import commons.Adresse;
import commons.Date;

public class Client {
	private String nom;
	private String prenom;
	private Adresse adresse;
	private Date dateDeNaissance;
	private String mail;
	private int numerotel;
	private boolean carteFidelite;
	private int pointFidelite; 
	
	public Client(String nom,String prenom,Adresse adresse,Date dateDeNaissance,String mail,int numerotel,boolean cartefidelite,int pointfidelite) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.dateDeNaissance = dateDeNaissance;
		this.mail = mail;
		this.numerotel = numerotel;
		this.carteFidelite = cartefidelite;
		this.pointFidelite = pointfidelite;
	}

	public Adresse getAdresse() {
		return adresse;
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

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
}
