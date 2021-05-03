package commons;

public class Adresse {
    private String voie;
    private String nVoie;
    private String codePostal;
    private String ville;

    public Adresse(String voie, String nVoie, String codePostal, String ville) {
        this.voie = voie;
        this.nVoie = nVoie;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public String getVille() {
        return ville;
    }

    @Override
    public String toString() {
        return this.nVoie + " " + this.voie + " " + this.ville + " " + this.codePostal;
    }

    public String toDB() {
        return this.nVoie + ";" + this.voie + ";" + this.codePostal + ";" + this.ville;
    }

    public void fromDB(String in) {
        String[] split = in.split(";");
        this.nVoie = split[0];
        this.voie = split[1];
        this.codePostal = split[2];
        this.ville = split[3];

    }

    public String getVoie() {
        return voie;
    }

    public String getnVoie() {
        return nVoie;
    }

    public String getCodePostal() {
        return codePostal;
    }
}
