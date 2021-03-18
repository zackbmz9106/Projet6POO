package magasin;

public class MoyenDePaiment {
	private final String typedepaiement ;
	private final float valeur;
	
	public MoyenDePaiment(String typedepaiement,float valeur) {
		this.typedepaiement = typedepaiement;
		this.valeur = valeur;
	}

	public String getTypedepaiement() {
		return typedepaiement;
	}

	public float getValeur() {
		return valeur;
	}

}
