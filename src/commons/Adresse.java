package commons;

public class Adresse {
	private String typeVoie;
	private String voie;
	private int nVoie;
	private String ville;
	private int codePostal ;
	
	public Adresse(String typeVoie, String voie,int nVoie,String ville,int codePostal) {
		this.typeVoie = typeVoie;
		this.voie = voie;
		this.nVoie = nVoie;
		this.ville = ville;
		this.codePostal = codePostal;
	}
	public String toString() {
		return  this.nVoie + this.typeVoie + this.nVoie +this.voie + this.ville + this.codePostal;
	}
}
