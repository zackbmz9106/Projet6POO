package commons;

public class Adresse {
    private final String typeVoie;
    private final String voie;
    private final int nVoie;
    private final String ville;
    private final int codePostal;

    public Adresse(String typeVoie, String voie, int nVoie, String ville, int codePostal) {
        this.typeVoie = typeVoie;
        this.voie = voie;
        this.nVoie = nVoie;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public String toString() {
        return this.nVoie + this.typeVoie + this.nVoie + this.voie + this.ville + this.codePostal;
    }
    public void fromString(String in){

    }
}
