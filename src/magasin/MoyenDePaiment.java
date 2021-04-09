package magasin;

public class MoyenDePaiment {
    private final float valeur;
    private String typeDePaiement;

    public MoyenDePaiment(String typeDePaiement, float valeur) {
        this.typeDePaiement = typeDePaiement;
        this.valeur = valeur;
    }

    public String getTypeDePaiement() {
        return typeDePaiement;
    }

    public float getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return this.typeDePaiement + this.valeur;
    }


    public void fromString(String typePaiement) {
        //TODO
        this.typeDePaiement = typePaiement;
    }
}
