package magasin;

import java.util.ArrayList;

public class Vente {
	private final ArrayList<Produit> listeArticle;
	private final ArrayList<Integer> quantite;
	private final float prixArticle ;
	private final float reduction;
	private final MoyenDePaiment typePaiement;
	
	public Vente(ArrayList<Produit> listeArticle,ArrayList<Integer> quantite,float prixArticle,float reduction,MoyenDePaiment typePaiement) {
		this.listeArticle = listeArticle;
		this.quantite = quantite;
		this.prixArticle = prixArticle;
		this.reduction = reduction;
		this.typePaiement = typePaiement;
	}
	

}
