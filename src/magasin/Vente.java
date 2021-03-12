package magasin;

import java.util.ArrayList;

public class Vente {
	private ArrayList<Produit> listeArticle;
	private ArrayList<Integer> quantite;
	private float prixArticle ;
	private float reduction;
	private MoyenDePaiment typePaiement;
	
	public Vente(ArrayList<Produit> listeArticle,ArrayList<Integer> quantite,float prixArticle,float reduction,MoyenDePaiment typePaiement) {
		this.listeArticle = listeArticle;
		this.quantite = quantite;
		this.prixArticle = prixArticle;
		this.reduction = reduction;
		this.typePaiement = typePaiement;
	}
	

}
