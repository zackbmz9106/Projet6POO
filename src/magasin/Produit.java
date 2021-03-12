package magasin;

public class Produit {
	private String typeArticle;
	private String marque;
	private String nomArticle;
	private float prixArticle;
	private boolean isSolde;
	private float solde;
	
	public Produit(String typeArticle,String marque,String nomArticle,float prixArticle,boolean isSolde,float solde) {
		this.typeArticle = typeArticle;
		this.marque = marque;
		this.nomArticle = nomArticle;
		this.prixArticle = prixArticle;
		this.isSolde = isSolde;
		this.solde = solde;
	}

	public String getTypeArticle() {
		return typeArticle;
	}

	public String getMarque() {
		return marque;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public float getPrixArticle() {
		return prixArticle;
	}

	public boolean isSolde() {
		return isSolde;
	}

	public float getSolde() {
		return solde;
	}
	
}
