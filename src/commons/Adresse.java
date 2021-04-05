package commons;

public class Adresse {
    private  String voie ;
    private String nVoie;
    private  String codePostal;

    public Adresse(String voie, String nVoie, String codePostal) {
        this.voie = voie;
        this.nVoie = nVoie;
        this.codePostal = codePostal;
    }
    @Override
    public String toString() {
        return this.nVoie  + this.voie  + this.codePostal;
    }

    public String toDB(){
        return this.nVoie + ";" + this.voie + ";"+ this.codePostal;
    }
    public void fromDB(String in){
        String[] split = in.split(";");
        this.nVoie = split[0];
        this.voie = split[1];
        this.codePostal = split[2];

    }
}
